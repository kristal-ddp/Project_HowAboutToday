package com.phoenix.howabouttoday.payment.controller;


import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.Service.MemberService;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.member.entity.Role;
import com.phoenix.howabouttoday.payment.dto.CouponRequestDTO;
import com.phoenix.howabouttoday.payment.dto.CouponResponseDTO;
import com.phoenix.howabouttoday.payment.service.CouponService;
import com.phoenix.howabouttoday.payment.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RestOrdersController {

    private final MemberService memberService;
    private final OrdersService orderService;
    private final CouponService couponService;


    @GetMapping("/cartDuplCheck")
    public String cartDuplCheck(Model model, @LoginUser SessionDTO sessionDTO, Long roomNum){

        if (sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }
//        else {
//            sessionDTO = new SessionDTO(1l, "aaa@naver.com", "123", "이동우", "010-1234-5678", Role.MEMBER);
//        }
        MemberDTO customer = memberService.getSessionUser(sessionDTO.getMemberNum());

        if (orderService.cartDuplCheck(customer, roomNum)){
            return "{\"data\":true}";
        }
        return "{\"data\":false}";
    }

    @PostMapping("/couponUsed")
    public CouponResponseDTO discountCalculate(Model model, @LoginUser SessionDTO sessionDTO, @RequestBody CouponRequestDTO couponRequestDTO){

        if (sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }
//        else {
//            sessionDTO = new SessionDTO(1l, "aaa@naver.com", "123", "이동우", "010-1234-5678", Role.MEMBER);
//        }
        CouponResponseDTO couponResponseDTO = couponService.calculateCoupon(sessionDTO.getMemberNum(), couponRequestDTO);

        return couponResponseDTO;
    }
}
