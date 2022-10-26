package com.phoenix.howabouttoday.payment.service;

import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import com.phoenix.howabouttoday.payment.dto.OrdersDeleteDTO;
import com.phoenix.howabouttoday.payment.dto.OrdersDetailVO;
import com.phoenix.howabouttoday.payment.dto.OrdersCreateDTO;
import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.payment.entity.OrdersDetail;
import com.phoenix.howabouttoday.payment.repository.AvailableDateRepository;
import com.phoenix.howabouttoday.payment.repository.OrdersRepository;
import com.phoenix.howabouttoday.reserve.domain.CartRepository;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.reserve.domain.Reservation.ReserveStatus;
import com.phoenix.howabouttoday.room.entity.AvailableDate;
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
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class OrdersService {

    private final CartRepository cartRepository;
    private final AvailableDateRepository availableDateRepository;
    private final MemberRepository memberRepository;
    private final OrdersRepository ordersRepository;

    /** 장바구니에서 넘어오는 카트정보를 보여줌. **/
    public List<OrdersDetailVO> getCartData(List<Long> cartNum){
        return cartRepository.findAllById(cartNum)
                .stream()
                .map(OrdersDetailVO::new)
                .collect(Collectors.toList());
    }

    /** 결제가 완료되면 해당 결제정보 저장 **/
    public boolean savePaymentData(Long memberNum, OrdersCreateDTO ordersRequestDTO){
        try {
            Member member = memberRepository.findById(memberNum).get();
            List<Cart> cartList = cartRepository.findAllById(ordersRequestDTO.getCartNum());

            Orders order = getOrder(ordersRequestDTO, member, cartList);

            List<OrdersDetail> lists = cartList // Entity List
                    .stream() // Entity Stream
                    .map(cart -> ordersNumberMapping(cart, order)) // DTO Stream
                    .collect(Collectors.toList()); // DTO List

            order.getReservation().addAll(lists);

            ordersRepository.save(order);
            cartRepository.deleteAllById(ordersRequestDTO.getCartNum());
        }
        catch (RuntimeException e){
            System.out.println(e.toString());
            return false;
        }
        return true;
    }

    /** 결제 저장시 새로운 결제정보를 생성해서 돌려줌. **/
    /** 왠지 이건 orders 클래스 내부에서 해도 될거 같은데... **/
    private Orders getOrder(OrdersCreateDTO ordersRequestDTO, Member member, List<Cart> cartList) {
        Orders order = Orders.builder()
                .member(member)
                .ordersName(ordersRequestDTO.getName())
                .ordersTel(ordersRequestDTO.getTel())
                .ordersDate(LocalDate.now())
                .ordersPrice(getTotalPrice(cartList
                    .stream()
                    .map(cart -> cart.getReserveNum())
                    .collect(Collectors.toList())))
                .ordersType(ordersRequestDTO.getOrdersType())
                .ordersStatus(ReserveStatus.READY.getValue())
                .merchantId(ordersRequestDTO.getMerchantId())
                .impUid(ordersRequestDTO.getImp_uid())
                .build();
        return order;
    }

    /** 결제페이지에서 보여줄 총 금액을 구함. **/
    public Integer getTotalPrice(List<Long> cartNum){
        List<Cart> cartList = cartRepository.findAllById(cartNum);
        return cartList
                .stream()
                .mapToInt(Cart::getReservePrice)
                .sum();
    }
    
    /** 결제 완료시 cart에 있는 정보를 orderDetail로 변환해서 저장 **/
    private OrdersDetail ordersNumberMapping(Cart cart, Orders order){

        //예약 날짜를 룸에다가 넣어준다.
        LocalDate startDate = cart.getReserveUseStartDate();
        LocalDate endDate =  cart.getReserveUseEndDate();

        Period period = Period.between(startDate, endDate);
        System.out.println("날짜차이: " + period.getDays());

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
                .build();

        for (int i = 0; i < period.getDays(); i++) {
            AvailableDate ad = AvailableDate.builder()
                    .date(startDate.plusDays(i))
                    .room(od.getRoom())
                    .build();
            od.getRoom().getAvailableDate().add(ad);
        }
        return od;
    }


    public void cancelOrders(OrdersDeleteDTO ordersDeleteDTO){
        String accessToken = getToken();

        Orders orders = ordersRepository.findByMerchantId(ordersDeleteDTO.getMerchant_uid()).orElseThrow(() -> new IllegalArgumentException("uid가 없습니다."));

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("reason", "단순변심");
        body.add("imp_uid", orders.getImpUid());
        body.add("amount", ordersDeleteDTO.getCancel_request_amount().toString());
        body.add("checksum", ordersDeleteDTO.getCancel_request_amount().toString());
        
        /** 가상계좌를 위한 환불 계좌. 등록이 필요하기 때문에 가상계좌는 삭제 예정 **/
        body.add("refund_holder", "김영운");
        body.add("refund_bank", "88");
        body.add("refund_account", "110218317400");

        Mono<String> result = WebClient.create() //생성방법
                .post() //요청방식
                .uri("https://api.iamport.kr/payments/cancel")   //요청 uri
                .contentType(MediaType.APPLICATION_JSON)  //보내는 데이터의 형식
                .bodyValue(body)    //body에 담을 데이터. 이것 말고도 다른 형식도 있다.
                .accept(MediaType.APPLICATION_JSON) //받을 데이터의 형식
                .header("Authorization", accessToken)
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
         System.out.println(jsonObject.get("message"));

//        ordersRepository.deleteById(ordersNum);
    }


    public String getToken(){

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("imp_key", "3220511523750621");
        body.add("imp_secret", "Wlbixtnz3pYJ6wegOQ7FCJ0RRC3DGwDYsspAVKWWPv8z3mFEi5mlI663orvNeIm15VaypSopH8ujfoe7");


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
        JSONObject response = (JSONObject)jsonObject.get("response");
        return (String)response.get("access_token");
    }
}
