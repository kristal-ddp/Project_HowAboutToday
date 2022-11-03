/**
 * 
 * 예약내역과 관련된 처리를 모아놓은 컨트롤러
 *
 */

package com.phoenix.howabouttoday.payment.controller;

import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.Service.MemberService;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.member.entity.Role;
import com.phoenix.howabouttoday.payment.dto.OrdersDTO;
import com.phoenix.howabouttoday.payment.service.PaymentHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

import static com.phoenix.howabouttoday.payment.MemberDTOCHECK.doCheck;

@AllArgsConstructor
@Controller
@Slf4j
public class PaymentHistoryController {

    private final MemberService memberService;
    private final PaymentHistoryService paymentHistoryService;

    /* 마이페이지-결제내역  */
    @GetMapping(value = {"user-dashboard-booking/{page}", "user-dashboard-booking"})
    public String getUserBooking(@LoginUser SessionDTO sessionDTO,  @PathVariable(required = false, name = "page") Optional<Integer> page, Model model) {
//

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }
//        else{
//            sessionDTO = new SessionDTO(1l, "a@com", "123", "토에이", "010-9876-5432", Role.MEMBER);
//            model.addAttribute("sessionDTO", sessionDTO);
//        }

        doCheck(model);

        Integer curPage = page.orElse(1);
        MemberDTO customer = memberService.getSessionUser(sessionDTO.getMemberNum());
        Page<OrdersDTO> ordersDTOList = paymentHistoryService.pagingAllByMember(PageRequest.of(curPage - 1, 5, Sort.by("ordersNum").ascending()), customer.getNum());

        model.addAttribute("customer", customer);
        model.addAttribute("ordersDTOList", ordersDTOList);
        return "member/userdashboard/user-dashboard-booking";
    }

    @PostMapping("user-dashboard-booking")
    public String postUserDashboard() {
        return "member/userdashboard/user-dashboard-booking";
    }

    /* 마이페이지-예약탭-결제상세내역  */
    @GetMapping(value = {"bookingDetail/{page}"})
    public String getUserOrderDetail(@LoginUser SessionDTO sessionDTO, Model model, Principal principal, @PathVariable(name = "page") Long page){


        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }
//        else{
//            sessionDTO = new SessionDTO(1l, "aaa@naver.com", "123", "이동우", "010-1234-5678", Role.MEMBER);
//            model.addAttribute("sessionDTO", sessionDTO);
//        }

        /**
         * 1. get방식
         * 2. 회원정보
         * 3. 오더번호
         * 4.
         */

        doCheck(model);

        Long ordersNum = page;

        MemberDTO customer = memberService.getSessionUser(sessionDTO.getMemberNum());
        OrdersDTO ordersDTO = paymentHistoryService.getOrdersDTO(ordersNum);
        model.addAttribute("customer", customer);
        model.addAttribute("ordersDTO", ordersDTO);
        return "reserve/bookingDetails";
    }

    @PostMapping("bookingDetail")
    public String postUserOrderDetail() {

        return "reserve/bookingDetails";
    }
}
