package com.phoenix.howabouttoday.payment.service;

import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import com.phoenix.howabouttoday.payment.dto.OrdersDetailDTO;
import com.phoenix.howabouttoday.payment.dto.OrdersDTO;
import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.payment.entity.OrdersDetail;
import com.phoenix.howabouttoday.payment.repository.OrdersRepository;
import com.phoenix.howabouttoday.reserve.domain.CartRepository;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrdersService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final OrdersRepository ordersRepository;
    public List<OrdersDetailDTO> getCartData(Long memberId){
        List<Cart> cartList = cartRepository.findAllByMember_MemberNum(memberId);
        List<OrdersDetailDTO> orderDtoList = new ArrayList<>();

        cartList.forEach(cart -> {
            orderDtoList.add(cart.transDto());
        });
        return orderDtoList;
    }

    public List<OrdersDTO> getOrdersDto(Long memberNum){
        List<OrdersDTO> lists = ordersRepository.findAllByMember_MemberNum(memberNum)
                .stream()
                .map(order -> new OrdersDTO(order)) // 적용 후
                .collect(Collectors.toList());
        return lists;
    }

    public boolean savePaymentData(Long memberNum, String name, String tel){

        Member member = memberRepository.findById(memberNum).get();

        Orders order = Orders.builder()
                .member(member)
                .ordersName(name)
                .ordersTel(tel)
                .ordersDate(LocalDate.now())
                .ordersPrice(getTotalPrice(member.getMemberNum()))
                .ordersType("가상계좌")
                .ordersStatus("이용 후")
                .build();

        List<Cart> cartList = cartRepository.findAllByMember_MemberNum(member.getMemberNum());

//        List<OrdersDetail> lists = cartList // Entity List
//                .stream() // Entity Stream
//                .map(cart -> new OrdersDetail(cart, order)) // DTO Stream
//                .collect(Collectors.toList()); // DTO List

//        order.getReservation().addAll(lists);

        ordersRepository.save(order);
        return true;
    }

    public Integer getTotalPrice(Long memberId){
        List<Cart> cartList = cartRepository.findAllByMember_MemberNum(memberId);
        Integer totalPrice = 0;

        for (Cart cart: cartList) {
            totalPrice += cart.getReservePrice();
        }
        return totalPrice;
    }
}

