package com.phoenix.howabouttoday.payment.controller;

import com.phoenix.howabouttoday.accom.service.AccomodationService;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.payment.dto.OrdersDTO;
import com.phoenix.howabouttoday.payment.service.MemberServiceCopy;
import com.phoenix.howabouttoday.payment.service.OrdersService;
import com.phoenix.howabouttoday.room.service.RoomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Controller
@Slf4j
public class PaymentController {


    private final MemberServiceCopy memberServiceCopy;
    private final AccomodationService accomodationService;
    private final RoomService roomService;
    private final OrdersService orderService;

    @GetMapping("user-dashboard-booking-details")
    public String getUserDashboardSettings(Model model) {

        MemberDTO customer = memberServiceCopy.getCustomer(1L);
        List<OrdersDTO> orders = orderService.getOrdersDto(customer.getNum());
        model.addAttribute("orders", orders);
        System.out.println("결제 내역 페이지");

        return "reserve/payment-received";
    }

    @PostMapping("user-dashboard-booking-details")
    public String postUserDashboardSettings() {

        return "reserve/payment-received";
    }

    @GetMapping("user-dashboard-booking")
    public String getUserDashboard(Principal principal, Authentication authentication, Model model) {
        /*********************로그인 후 인증정보를 가져 오는 부분 작성***********************/
        MemberDTO customer = memberServiceCopy.getCustomer(1L);

        List<OrdersDTO> orders = orderService.getOrdersDto(customer.getNum());

        model.addAttribute("orders", orders);

        System.out.println("결제 내역 페이지");


        return "member/userdashboard/user-dashboard-booking";
    }
    @PostMapping("user-dashboard-booking")
    public String postUserDashboard() {
        return "member/userdashboard/user-dashboard-booking";
    }

}
