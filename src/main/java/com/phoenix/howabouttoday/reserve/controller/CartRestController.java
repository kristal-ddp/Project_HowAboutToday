package com.phoenix.howabouttoday.reserve.controller;

import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import com.phoenix.howabouttoday.reserve.domain.CartRepository;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.reserve.service.CartService;
import com.phoenix.howabouttoday.reserve.service.ReserveForm;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/cart")
@RequiredArgsConstructor
public class CartRestController {

    private final CartService cartService;
    private final MemberRepository memberRepository;//아직 회원이없어서 테스트용 회원조회에 필요
    private final CartRepository cartRepository;
    /** 장바구니 저장 **/
    @PostMapping("/{roomNum}")
    public boolean save(@LoginUser SessionDTO user,
                        @RequestBody CartForm cartForm,
                        @PathVariable Long roomNum
                        ){

        /** 회원 조회 로직 **/
        Long memberNum = user.getMemberNum();


        /** 멀티데이트를 스플릿해서 reserveForm에 넘겨주기위한 준비작업 **/
        String[] splitDate = cartForm.getCheckDate().split("-");
        //날짜 패턴에 공백이 있어서 양쪽 공백제거 작업
        String startDate = splitDate[0].strip();
        String endDate = splitDate[1].strip();

        /**reserveForm에 cartForm을 파싱해서 넣어주는 로직 **/
        ReserveForm reserveForm = ReserveForm.builder()
                .reserveUseStartDate(StringToParseDate(startDate))
                .reserveUseEndDate(StringToParseDate(endDate))
                .reserveAdultCount(cartForm.getAdultQty())
                .reserveChildCount(cartForm.getChildQty())
                .build();


        if(cartService.checkCart(memberNum,roomNum)){
            /*cart가 존재한다면 */
            return true;
        }else{
            /* cart가 존재하지 않는다면 장바구니에 저장*/
            cartService.save(memberNum,roomNum,reserveForm); //임시 룸넘버를 보냈음 테스트하기위함
            return false;
        }

    }

    /** 장바구니 페이지에서 특정 객실 삭제 **/
    @DeleteMapping("/{cartNum}")
    public ResponseEntity deleteByNum(@PathVariable Long cartNum){

        System.out.println("cartNum = " + cartNum);
        cartService.deleteByNum(cartNum);

        return new ResponseEntity(HttpStatus.OK);
    }


    /** 스트링타입을 LocalDate타입으로 파싱해주는 메서드 **/
    public LocalDate StringToParseDate(String date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate parseDate = LocalDate.parse(date, formatter);
        return parseDate;
    }
}
