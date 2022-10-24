package com.phoenix.howabouttoday.reserve.controller;

import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import com.phoenix.howabouttoday.reserve.service.CartDto;
import com.phoenix.howabouttoday.reserve.service.CartService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final MemberRepository memberRepository;//아직 회원이없어서 테스트용 회원조회에 필요
    @GetMapping
    public String findAll(@LoginUser SessionDTO user,
                          Model model){

        /** 회원 조회 로직 **/
        Long memberNum = user.getMemberNum();

        /* 장바구니 존재 여부 확인 */
        boolean checkCart = cartService.checkHaveCart(memberNum);

        if(checkCart){

            /* 장바구니가 존재한다면 */
            int totalPrice = 0;

            /* 장바구니 목록 반환 */
            List<CartDto.ResponseDto> cartList = cartService.getListMemberNum(memberNum);

            /* 가격 총계 계산 */
            for (CartDto.ResponseDto cart : cartList) {
                totalPrice += cart.getReservePrice();

                Period between = Period.between(cart.getReserveUseStartDate(), cart.getReserveUseStartDate());

            }

            model.addAttribute("cartList",cartList);
            model.addAttribute("totalPrice",totalPrice);

        }

        /* 장바구니 존재 여부 반환 */
        model.addAttribute("checkCart",checkCart);
        /*헤더에 필요한 sessionDTO 반환 */
        model.addAttribute("sessionDTO",user);

        return "reserve/cart";
    }


//    OrdersController로 이동
//    @GetMapping("/test")
//    @ResponseBody
//    public String cartList(@RequestParam Long[] cartNum){
//
//        for (Long aLong : cartNum) {
//            System.out.println("aLong = " + aLong);
//        }
//
//
//
//        return "hi";
//    }





    public Member getTestMember(){
        Optional<Member> optionalMember = memberRepository.findById(1L);
        Member findMember = optionalMember.get();
        return findMember;
    }
}
