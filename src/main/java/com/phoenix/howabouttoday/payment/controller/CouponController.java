package com.phoenix.howabouttoday.payment.controller;

import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.payment.dto.CouponDTO;
import com.phoenix.howabouttoday.payment.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @GetMapping("user-dashboard-coupon")
    public String getUserDashboardProfile(@LoginUser SessionDTO sessionDTO, Model model) {

        if (sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        Long memberNum = sessionDTO.getMemberNum();

        List<CouponDTO> cList = couponService.findAll_Coupon(memberNum);
        model.addAttribute("clist",cList);

        return "member/userdashboard/user-dashboard-coupon";

    }

}
