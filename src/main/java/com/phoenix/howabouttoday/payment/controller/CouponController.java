package com.phoenix.howabouttoday.payment.controller;

import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.payment.dto.CouponDTO;
import com.phoenix.howabouttoday.payment.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.phoenix.howabouttoday.payment.MemberDTOCHECK.doCheck;

@Controller
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @GetMapping("user-dashboard-coupon")
    public String getUserDashboardProfile(@LoginUser SessionDTO sessionDTO, Model model, @RequestParam(value="roomNum",required=false) Long memberNum) {

        if (sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        doCheck(model);

        /** 회원 Object 반환하는 로직 **/
        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("memberDTO",memberDTO);
        boolean memberCheck = false;
        model.addAttribute("memberCheck",memberCheck);

        List<CouponDTO> cList = couponService.getCoupon(memberNum);
        model.addAttribute("clist",cList);

        return "member/userdashboard/user-dashboard-coupon";

    }

}
