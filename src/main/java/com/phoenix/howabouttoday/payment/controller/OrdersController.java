/**
 * 결제와 관련된 처리들을 모아놓은 컨트롤러
 */

package com.phoenix.howabouttoday.payment.controller;

import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.Service.MemberService;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.member.entity.Role;
import com.phoenix.howabouttoday.payment.dto.OrdersDeleteDTO;
import com.phoenix.howabouttoday.payment.dto.OrdersDetailVO;
import com.phoenix.howabouttoday.payment.dto.OrdersCreateDTO;
import com.phoenix.howabouttoday.payment.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

@RequestMapping("/orders")
@RequiredArgsConstructor
@Controller
public class OrdersController {

    private final OrdersService orderService;
    private final MemberService memberService;


    /* 카드 -> 결제페이지 */
    @GetMapping("/payment")
    public String paymentView(@LoginUser SessionDTO sessionDTO, Model model, @RequestParam List<Long> cartNum, Principal principal) {

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

        if (sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        } else {
            sessionDTO = new SessionDTO(1l, "aaa@naver.com", "123", "이동우", "010-1234-5678", Role.MEMBER);
        }

        //1. 시큐리티를 사용해서 principal 객체에서 user정보를 가져와서 memberNum을 알 수 있다.

        MemberDTO customer = memberService.getSessionUser(sessionDTO.getMemberNum());
        List<OrdersDetailVO> infoList = orderService.getCartData(cartNum);
        Integer totalPrice = orderService.getTotalPrice(cartNum);   //얘를 따로 이렇게 하는 게 맞을까??

        model.addAttribute("totalPrice", totalPrice);

        model.addAttribute("customer", customer);
        model.addAttribute("infoList", infoList);
        return "reserve/checkout";
    }

    @PostMapping("checkout")
    public String postCheckout() {
        return "reserve/checkout";
    }

    /* 주문삭제 */
    /* 주문은 삭제가 아니라 취소로 표시해두고 여러가지 제한을 두는 게 맞을 것 같기도 하다. */
    @PostMapping("/deleteorders")
    @ResponseBody
    public OrdersDeleteDTO getDelete(@LoginUser SessionDTO sessionDTO, @RequestBody OrdersDeleteDTO data) {

        System.out.println("잘 들어오니?");

        System.out.println(orderService.getToken());
        orderService.cancelOrders(data);


//        orderService.cancelOrders(ordersNum);
        return data;
    }


    /* 결제 get방식 요청을 post리다이렉트 */
    @GetMapping("/paymentSuccess")
    public String getUserPaymentSuccess() {
        return "redirect:/home";
    }

    /* 결제 성공 */
    @PostMapping("/paymentSuccess")
    public String postUserPaymentSuccess(@LoginUser SessionDTO sessionDTO, OrdersCreateDTO ordersRequestDTO) {


        /** 해결 완료! **/
        /** 결제 완료 요청이 csrf로 인해서 막혔다. 정확히 뭐가 문제인지는 파악해보자. **/
        /** 이것만 제대로 되면 결제 취소도 가능할듯. **/

        if (sessionDTO != null) {
//            model.addAttribute("sessionDTO", sessionDTO);
        } else {
            sessionDTO = new SessionDTO(1l, "aaa@naver.com", "123", "이동우", "010-1234-5678", Role.MEMBER);
        }

//        model.addAttribute("sessionDTO", sessionDTO);
        MemberDTO customer = memberService.getSessionUser(sessionDTO.getMemberNum());
        orderService.savePaymentData(customer.getNum(), ordersRequestDTO);
        return "redirect:/home";
    }
}