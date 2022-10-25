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
import com.phoenix.howabouttoday.member.entity.Code;
import com.phoenix.howabouttoday.payment.dto.OrdersDTO;
import com.phoenix.howabouttoday.payment.service.PaymentHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@AllArgsConstructor
@Controller
@Slf4j
public class PaymentHistoryController {

    private final MemberService memberService;
    private final PaymentHistoryService paymentHistoryService;


    /* 마이페이지-예약탭  */
    @GetMapping(value = {"user-dashboard-booking/{page}", "user-dashboard-booking"})
    public String getUserBooking(@LoginUser SessionDTO sessionDTO,  @PathVariable(required = false, name = "page") Optional<Integer> page, Model model) {
//

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }
        else{
            sessionDTO = new SessionDTO(1l, "aaa@naver.com", "123", "이동우", "010-1234-5678", Code.MEMBER);
            model.addAttribute("sessionDTO", sessionDTO);
        }


        Integer curPage = page.orElse(1);

        //1. 시큐리티를 사용해서 principal 객체에서 user정보를 가져와서 memberNum을 알 수 있다.

//        MemberDTO customer = memberService.getCustomer(2L); //여긴 페이징 처리 테스트 하느라 고정해둠
        MemberDTO customer = memberService.getSessionUser(sessionDTO.getMemberNum());
//        MemberDTO customer = memberService.getAuthUser(principal.getName());s

        Page<OrdersDTO> ordersDTOList = paymentHistoryService.pagingAllByMember(PageRequest.of(curPage - 1, 5, Sort.Direction.DESC, "ordersDate"), customer.getNum());

//        Page<OrdersDTO> ordersDTOList = orderService.pagingAll(PageRequest.of(curPage - 1, 5, Sort.Direction.DESC, "ordersDate"));

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
        else{
            sessionDTO = new SessionDTO(1l, "aaa@naver.com", "123", "이동우", "010-1234-5678", Code.MEMBER);
            model.addAttribute("sessionDTO", sessionDTO);
        }

        /**
         * 1. get방식
         * 2. 회원정보
         * 3. 오더번호
         * 4.
         */

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
