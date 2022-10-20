/**
 *
 * 장바구니에서 결제로 이동 시 처리하는 컨트롤러
 *
 */


package com.phoenix.howabouttoday.payment.controller;

import com.phoenix.howabouttoday.member.Service.MemberService;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.payment.dto.OrdersDetailDTO;
import com.phoenix.howabouttoday.payment.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrdersController {

    private final OrdersService orderService;
    private final MemberService memberService;

    @GetMapping("checkout")
    public String cartView(Model model/*, @PathVariable Integer id*/){
        /**
         * 객실 -> 결제 이동시 컨트롤러의 처리 순서
         * 1. 로그인 상태인가?(서큐리티로 체크)
         * 2. 어떤 회원인가?(서큐리티의 principle 객체에서 획득)
         * 3. 어떤 객실인가?(Get방식으로 객실의 PK값 받아서 서비스로 전달)
         *
         * 필요한 전체 데이터
         * 1. 회원DTO, 룸DTO, 예약 시작일, 종료일, 가격, 성인인원, 아이인원(아이는 할건지 말건지 확실히 정하기)
         * - 회원과 룸은 DTO로 받는 정보로, 꼭 필요한 정보만 있으면 된다.
         */

        //1. 시큐리티를 사용해서 principal 객체에서 user정보를 가져와서 memberNum을 알 수 있다.

        MemberDTO customer = memberService.getCustomer(1L);
        List<OrdersDetailDTO> infoList = orderService.getCartData(customer.getNum());
        Integer totalPrice = orderService.getTotalPrice(customer.getNum());
        System.out.println(totalPrice);


        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("customer", customer);
        model.addAttribute("infoList", infoList);
        return "reserve/checkout";
}

    @PostMapping("checkout")
    public String postCheckout(){
        return "reserve/checkout";
    }

    @GetMapping("paymentSuccess")
    public String getUserPaymentSuccess(Model model) {
        MemberDTO customer = memberService.getCustomer(1L);
        orderService.savePaymentData(customer.getNum(), "이동우", "010-1234-5678");


        return "redirect:/home";
    }

    @PostMapping("paymentSuccess")
    public String postUserPaymentSuccess() {

        return "redirect:/home";
    }

}