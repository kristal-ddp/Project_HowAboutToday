package com.phoenix.howabouttoday.payment.service;

import com.phoenix.howabouttoday.global.OrdersStatus;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import com.phoenix.howabouttoday.payment.dto.OrdersDeleteDTO;
import com.phoenix.howabouttoday.payment.dto.OrdersDetailVO;
import com.phoenix.howabouttoday.payment.dto.OrdersCreateDTO;
import com.phoenix.howabouttoday.payment.dto.OrdersDirectDTO;
import com.phoenix.howabouttoday.payment.entity.Coupon;
import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.payment.entity.OrdersDetail;
import com.phoenix.howabouttoday.payment.enumType.ReviewStatus;
import com.phoenix.howabouttoday.payment.repository.AvailableDateRepository;
import com.phoenix.howabouttoday.payment.repository.CouponRepository;
import com.phoenix.howabouttoday.payment.repository.OrdersRepository;
import com.phoenix.howabouttoday.reserve.domain.CartRepository;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.reserve.domain.Reservation.ReserveStatus;
import com.phoenix.howabouttoday.reserve.service.ReserveForm;
import com.phoenix.howabouttoday.room.entity.AvailableDate;
import com.phoenix.howabouttoday.room.entity.Room;
import com.phoenix.howabouttoday.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class OrdersService {

    private final CartRepository cartRepository;
    private final AvailableDateRepository availableDateRepository;
    private final MemberRepository memberRepository;
    private final OrdersRepository ordersRepository;
    private final RoomRepository roomRepository;
    private final CouponRepository couponRepository;



    public Boolean cartDuplCheck(MemberDTO memberDTO, Long roomNum){
        /** 장바구니에 동일한 내역이 존재한다면. **/
        if (cartRepository.existsByMember_MemberNumAndRoom_RoomNum(memberDTO.getNum(), roomNum)){
            return true;
        }
        return false;
    }


    /** 객실 -> 바로결제로 넘어오는 정보로 카트 객체만 만들어서 반환해줌. **/
    public List<OrdersDetailVO> getDirectData(MemberDTO memberDTO, OrdersDirectDTO ordersDirectDTO){

        Member member = memberRepository.findById(memberDTO.getNum()).orElseThrow(() -> new IllegalArgumentException(memberDTO.getNum() + "번 멤버 정보가 없습니다."));
        Room storeRoom = roomRepository.findById(ordersDirectDTO.getRoomNum()).orElseThrow(() -> new IllegalArgumentException(ordersDirectDTO.getRoomNum() + "번 Room 정보가 없습니다."));

        String[] splitDate = ordersDirectDTO.getDaterange().split("-");
        LocalDate startDate = StringToParseDate(splitDate[0].strip());
        LocalDate endDate = StringToParseDate(splitDate[1].strip());

        Period between = Period.between(startDate, endDate);

        Cart saveCart = cartRepository.save(Cart.builder()
                .member(member)
                .room(storeRoom)
                .reserveStatus(ReserveStatus.READY)
                .reserveUseStartDate(startDate)
                .reserveUseEndDate(endDate)
                .reservePrice(storeRoom.getPrice() * between.getDays())
                .reserveAdultCount(ordersDirectDTO.getAdult_qty())
                .reserveChildCount(ordersDirectDTO.getChild_qty())
                .build());

        OrdersDetailVO ordersDetailVO = new OrdersDetailVO(saveCart);

        List<OrdersDetailVO> ordersDetailVOList = new ArrayList<>();
        ordersDetailVOList.add(ordersDetailVO);

        return ordersDetailVOList;
    }


    /** 스트링타입을 LocalDate타입으로 파싱해주는 메서드 **/
    public LocalDate StringToParseDate(String date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate parseDate = LocalDate.parse(date, formatter);
        return parseDate;
    }


    /** 장바구니에서 넘어오는 카트정보를 보여줌. **/
    public List<OrdersDetailVO> getCartData(List<Long> cartNum){
        return cartRepository.findAllById(cartNum)
                .stream()
                .map(OrdersDetailVO::new)
                .collect(Collectors.toList());
    }

    /** 결제가 완료되면 해당 결제정보 저장 **/
    public boolean savePaymentData(Long memberNum, OrdersCreateDTO ordersCreateDTO){
        try {
            Member member = memberRepository.findById(memberNum).orElseThrow(() -> new IllegalArgumentException(String.format("%d번 멤버가 없습니다.", memberNum)));
            List<Cart> cartList = cartRepository.findAllById(ordersCreateDTO.getCartNum());


            /** 여기서부터 시작하면 됨. **/
            //현재 결제 시 할인 해준 가격과 쿠폰pk를 가지고 온다. 할인된(실제 결제된 금액)도 orders테이블에 넣어야 되나 고민이다.

            Orders order = getOrder(ordersCreateDTO, member, cartList);
            List<OrdersDetail> lists = cartList // Entity List
                    .stream() // Entity Stream
                    .map(cart -> ordersNumberMapping(cart, order)) // DTO Stream
                    .collect(Collectors.toList()); // DTO List

            order.getReservation().addAll(lists);

            Coupon coupon = couponRepository.findByCouponNumAndMember_MemberNum(ordersCreateDTO.getUseCouponNum(), memberNum).orElse(null);

            if(coupon != null){
                coupon.couponUsed();
            }

            ordersRepository.save(order);
            cartRepository.deleteAllById(ordersCreateDTO.getCartNum());
        }
        catch (RuntimeException e){
            System.out.println(e.toString());
            return false;
        }
        return true;
    }

    /** 결제 저장시 새로운 결제정보를 생성해서 돌려줌. **/
    /** 왠지 이건 orders 클래스 내부에서 해도 될거 같은데... **/
    private Orders getOrder(OrdersCreateDTO ordersCreateDTO, Member member, List<Cart> cartList) {
        Orders order = Orders.builder()
                .member(member)
                .ordersName(ordersCreateDTO.getName())
                .ordersTel(ordersCreateDTO.getTel())
                .ordersDate(LocalDateTime.now())
                .ordersPrice(getTotalPrice(cartList
                        .stream()
                        .map(Cart::getReserveNum)
                        .collect(Collectors.toList())))
                .ordersType(ordersCreateDTO.getOrdersType())
                .ordersStatus(OrdersStatus.PAYMENT_COMPLETE)
                .merchantId(ordersCreateDTO.getMerchantId())
                .impUid(ordersCreateDTO.getImp_uid())
                .couponNum(ordersCreateDTO.getUseCouponNum())
                .discountValue(ordersCreateDTO.getDiscountValue())
                .build();
        return order;
    }

    /** 결제페이지에서 보여줄 총 금액을 구함. **/
    public Integer getTotalPrice(List<Long> cartNum){
        List<OrdersDetailVO> ordersDetailVOList = cartRepository.findAllById(cartNum).stream()
                .map(OrdersDetailVO::new)
                .collect(Collectors.toList());

        return ordersDetailVOList.stream()
                .mapToInt(OrdersDetailVO::totalPrice).sum();
    }

    /** 결제 완료시 cart에 있는 정보를 orderDetail로 변환해서 저장 **/
    private OrdersDetail ordersNumberMapping(Cart cart, Orders order){

        //예약 날짜를 룸에다가 넣어준다.
        LocalDate startDate = cart.getReserveUseStartDate();
        LocalDate endDate =  cart.getReserveUseEndDate();

        Period period = Period.between(startDate, endDate);
        System.out.println("날짜차이: " + period.getMonths() * 30 + period.getDays());

        OrdersDetail od = OrdersDetail.builder()
                .member(order.getMember())
                .room(cart.getRoom())
                .orders(order)
                .reserveStatus(ReserveStatus.READY)
                .reserveUseStartDate(cart.getReserveUseStartDate())
                .reserveUseEndDate(cart.getReserveUseEndDate())
                .reservePrice(cart.getReservePrice())
                .reserveAdultCount(cart.getReserveAdultCount())
                .reserveChildCount(cart.getReserveChildCount())
                .isReviewWrited(ReviewStatus.PRE_WRITE)
                .build();

        for (int i = 0; i < period.getMonths() * 30 + period.getDays(); i++) {
            AvailableDate ad = AvailableDate.builder()
                    .date(startDate.plusDays(i))
                    .room(od.getRoom())
                    .build();
            od.getRoom().getAvailableDate().add(ad);
        }
        return od;
    }

    public void changeStatusOrders(Long ordersNum){
        if (ordersNum != -1){

            Orders orders = ordersRepository.findById(ordersNum).orElseThrow(()->new IllegalArgumentException("해당 Orders가 없습니다."));
            orders.getReservation()
                    .forEach(orderDetail -> {
                        LocalDate startDate = orderDetail.getReserveUseStartDate();
                        LocalDate endDate = orderDetail.getReserveUseEndDate().minusDays(1);
                        availableDateRepository.deleteAllByRoom_RoomNumAndOneDayBetween(orderDetail.getRoom().getRoomNum(), startDate, endDate);
                        System.out.println("췍");
                    });
            orders.changeToReadyState();
        }
    }
    public Long cancelOrders(OrdersDeleteDTO ordersDeleteDTO){

        Orders orders = ordersRepository.findByMerchantId(ordersDeleteDTO.getMerchant_uid()).orElseThrow(() -> new IllegalArgumentException("uid가 없습니다."));

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("reason", ordersDeleteDTO.getReason());
        body.add("imp_uid", orders.getImpUid());
        body.add("amount", ordersDeleteDTO.getCancel_request_amount().toString());
        body.add("checksum", ordersDeleteDTO.getCancel_request_amount().toString());

        /** 가상계좌를 위한 환불 계좌. 등록이 필요하기 때문에 가상계좌는 삭제 예정 **/
//        body.add("refund_holder", "김영운");
//        body.add("refund_bank", "88");
//        body.add("refund_account", "110218317400");

        Mono<String> result = WebClient.create() //생성방법
                .post() //요청방식
                .uri("https://api.iamport.kr/payments/cancel")   //요청 uri
                .contentType(MediaType.APPLICATION_JSON)  //보내는 데이터의 형식
                .bodyValue(body)    //body에 담을 데이터. 이것 말고도 다른 형식도 있다.
                .accept(MediaType.APPLICATION_JSON) //받을 데이터의 형식
                .header("Authorization", getToken())
                .retrieve() //이게 실제로 요청을 보내는 메서드인듯
                .bodyToMono(String.class);  //여기는 응답을 받아서 처리해주는 부분인듯


        // System.out.println(result.block());
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) jsonParser.parse(result.block());
        }
        catch (ParseException e){
            System.out.println(e.toString());
        }
        System.out.println(jsonObject.get("code"));

        return ((Long)jsonObject.get("code") == 0) ? orders.getOrdersNum() : -1;
    }

    public String getToken(){
        String IMP_KEY = "3220511523750621";
        String IMP_SECRET = "Wlbixtnz3pYJ6wegOQ7FCJ0RRC3DGwDYsspAVKWWPv8z3mFEi5mlI663orvNeIm15VaypSopH8ujfoe7";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("imp_key", IMP_KEY);
        body.add("imp_secret", IMP_SECRET);

        Mono<String> result = WebClient.create() //생성방법
                .post() //요청방식
                .uri("https://api.iamport.kr/users/getToken")   //요청 uri
//                .contentType(MediaType.APPLICATION_JSON)  //보내는 데이터의 형식
                .bodyValue(body)    //body에 담을 데이터. 이것 말고도 다른 형식도 있다.
                .accept(MediaType.APPLICATION_JSON) //받을 데이터의 형식
                .retrieve() //이게 실제로 요청을 보내는 메서드인듯
                .bodyToMono(String.class);  //여기는 응답을 받아서 처리해주는 부분인듯


        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(result.block());
        }
        catch (ParseException e){
            System.out.println(e.toString());
        }
        JSONObject response = ((JSONObject)jsonObject.get("response"));
        return (String)response.get("access_token");
    }
}
