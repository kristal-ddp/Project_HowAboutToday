/**
 * 결제와 관련된 처리들을 모아놓은 컨트롤러
 */

package com.phoenix.howabouttoday.payment.controller;

import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.Service.MemberService;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.member.entity.Role;
import com.phoenix.howabouttoday.payment.dto.*;
import com.phoenix.howabouttoday.payment.service.CouponService;
import com.phoenix.howabouttoday.payment.service.OrdersService;
import com.phoenix.howabouttoday.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

import static com.phoenix.howabouttoday.payment.MemberDTOCHECK.doCheck;

@RequiredArgsConstructor
@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService orderService;
    private final MemberService memberService;
    private final CouponService couponService;

    // 객실 상세 -> 결제 페이지
    @GetMapping("/directPayment")
    public String roomView(Model model, @LoginUser SessionDTO sessionDTO, Principal principal, OrdersDirectDTO ordersDirectDTO) {

        if (sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }
//        else {
//            sessionDTO = new SessionDTO(1l, "aaa@naver.com", "123", "이동우", "010-1234-5678", Role.MEMBER);
//        }

        doCheck(model);

        MemberDTO customer = memberService.getSessionUser(sessionDTO.getMemberNum());
        List<OrdersDetailVO> infoList = orderService.getDirectData(customer, ordersDirectDTO);

        return "redirect:/orders/payment?cartNum=" + infoList.get(0).getCartNum();
    }

    /* 카트 -> 결제페이지 */
    @GetMapping("/payment")
    public String paymentView(@LoginUser SessionDTO sessionDTO, Principal principal, Model model, @RequestParam List<Long> cartNum) {

        if (sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }
//        else {
//            sessionDTO = new SessionDTO(1l, "aaa@naver.com", "123", "이동우", "010-1234-5678", Role.MEMBER);
//        }

        doCheck(model);

        MemberDTO customer = memberService.getSessionUser(sessionDTO.getMemberNum());
        List<OrdersDetailVO> infoList = orderService.getCartData(cartNum);
        Integer totalPrice = orderService.getTotalPrice(cartNum);   //얘를 따로 이렇게 하는 게 맞을까??
        List<CouponDTO> couponDTOList = couponService.getCoupon(customer.getNum());


        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("customer", customer);
        model.addAttribute("infoList", infoList);
        model.addAttribute("couponList", couponDTOList);
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

        Long cancelOrdersNum = orderService.cancelOrders(data);
        orderService.changeStatusOrders(cancelOrdersNum);
        return data;
    }

    /* 결제 get방식 요청을 post리다이렉트 */
    @GetMapping("/paymentSuccess")
    public String getUserPaymentSuccess() {
        return "redirect:/home";
    }

    /* 결제 성공 */
    @PostMapping("/paymentSuccess")
    public String postUserPaymentSuccess(Model model, @LoginUser SessionDTO sessionDTO, OrdersCreateDTO ordersCreateDTO) {


        /** 해결 완료! **/
        /** 결제 완료 요청이 csrf로 인해서 막혔다. 정확히 뭐가 문제인지는 파악해보자. **/
        /** 이것만 제대로 되면 결제 취소도 가능할듯. **/

        if (sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }
//        else {
//            sessionDTO = new SessionDTO(1l, "aaa@naver.com", "123", "이동우", "010-1234-5678", Role.MEMBER);
//        }

        doCheck(model);

//        model.addAttribute("sessionDTO", sessionDTO);
        MemberDTO customer = memberService.getSessionUser(sessionDTO.getMemberNum());
        orderService.savePaymentData(customer.getNum(), ordersCreateDTO);
        return "redirect:/home";
    }
}