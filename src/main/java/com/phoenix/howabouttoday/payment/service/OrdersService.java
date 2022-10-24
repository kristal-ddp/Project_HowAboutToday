package com.phoenix.howabouttoday.payment.service;

import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import com.phoenix.howabouttoday.payment.dto.OrdersDetailVO;
import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.payment.entity.OrdersDetail;
import com.phoenix.howabouttoday.payment.repository.AvailableDateRepository;
import com.phoenix.howabouttoday.payment.repository.OrdersRepository;
import com.phoenix.howabouttoday.reserve.domain.CartRepository;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.reserve.domain.Reservation.ReserveStatus;
import com.phoenix.howabouttoday.room.entity.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public boolean savePaymentData(Long memberNum, String name, String tel, String ordersType, List<Long> cartNum){
        try {
            Member member = memberRepository.findById(memberNum).get();
            List<Cart> cartList = cartRepository.findAllById(cartNum);

            Orders order = getOrder(name, tel, ordersType, member, cartList);

            List<OrdersDetail> lists = cartList // Entity List
                    .stream() // Entity Stream
                    .map(cart -> ordersNumberMapping(cart, order)) // DTO Stream
                    .collect(Collectors.toList()); // DTO List

            order.getReservation().addAll(lists);

            ordersRepository.save(order);
            cartRepository.deleteAllById(cartNum);
        }
        catch (RuntimeException e){
            System.out.println(e.toString());
            return false;
        }
        return true;
    }

    /** 결제 저장시 새로운 결제정보를 생성해서 돌려줌. **/
    /** 왠지 이건 orders 클래스 내부에서 해도 될거 같은데... **/
    private Orders getOrder(String name, String tel, String ordersType, Member member, List<Cart> cartList) {
        Orders order = Orders.builder()
                .member(member)
                .ordersName(name)
                .ordersTel(tel)
                .ordersDate(LocalDate.now())
                .ordersPrice(getTotalPrice(cartList
                .stream()
                .map(cart -> cart.getReserveNum())
                .collect(Collectors.toList())))
                .ordersType(ordersType)
                .ordersStatus(ReserveStatus.READY.getValue())
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
}
