package com.phoenix.howabouttoday.reserve.service;

import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;

import java.util.List;

public interface CartService {

    /** memberNum에 해당하는 장바구니 존재 여부 확인  **/
    boolean checkHaveCart(Long memberNum);

    /** 해당 회원의 장바구니에서 모든 객실 삭제 **/
    void deleteAll(Long memberNum);

    /** 장바구니에서 특정 객실 삭제 **/
    void deleteByNum(Long cartNum);

    /** 장바구니에서 모든 리스트 반환 **/
    List<CartDto.ResponseDto> getListMemberNum(Long memberNum);

    /** 장바구니에 숙소가 존재하는지 확인 **/
    boolean checkCart(Long memberNum, Long roomNum);

    /** 장바구니 저장 **/
    Cart save(Long memberNum, Long roomNum, ReserveForm reserveForm);


}
