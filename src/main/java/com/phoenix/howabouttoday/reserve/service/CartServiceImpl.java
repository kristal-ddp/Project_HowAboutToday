package com.phoenix.howabouttoday.reserve.service;


import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import com.phoenix.howabouttoday.reserve.domain.CartRepository;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.room.entity.Room;
import com.phoenix.howabouttoday.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService{


    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;

    /** memberNum에 해당하는 장바구니 존재 여부 확인 **/
    @Override
    public boolean checkHaveCart(Long memberNum) {
        return cartRepository.existsByMember_MemberNum(memberNum);
    }


    /** memberNum의 장바구니 전부 삭제 **/
    @Override
    public void deleteAll(Long memberNum) {
        cartRepository.deleteAllByMember_MemberNum(memberNum);
    }

    /** 장바구니에서 특정 객실 삭제 **/
    @Override
    public void deleteByNum(Long cartNum) {
        Cart cart = cartRepository.findById(cartNum).orElseThrow(() ->
                new IllegalArgumentException("해당 장바구니 정보가 존재하지 않습니다"));

        cartRepository.delete(cart);

    }

    /** memberNum을 통해 cartList 받아옴 **/
    @Override
    public List<CartDto.ResponseDto> getListMemberNum(Long memberNum) {
        Member member = memberRepository.findById(memberNum).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));

        List<Cart> cartList = cartRepository.findAllByMember_MemberNum(member.getMemberNum());

        return cartList.stream().map(CartDto.ResponseDto::new).collect(Collectors.toList());

    }

    /** memberNum, roomNum에 해당하는 cart가 존재하는지 확인 - **/
    @Override
    public boolean checkCart(Long memberNum, Long roomNum) {
        return cartRepository.existsByMember_MemberNumAndRoom_RoomNum(memberNum,roomNum);
    }

    /** 장바구니 저장 **/
    @Override
    public Cart save(Long memberNum, Long roomNum , ReserveForm reserveForm) {
        Member member = memberRepository.findById(memberNum).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        Room room = roomRepository.findById(roomNum).orElseThrow(() ->
                new IllegalArgumentException("해당 숙소가 존재하지 않습니다."));

        /* DB에 cart 저장 */
        Cart saveCart = cartRepository.save(Cart.builder()
                .member(member)
                .room(room)
                .reserveUseStartDate(reserveForm.getReserveUseStartDate())
                .reserveUseEndDate(reserveForm.getReserveUseEndDate())
                .reserveAdultCount(reserveForm.getReserveAdultCount())
                .reserveChildCount(reserveForm.getReserveChildCount()).build());

        return saveCart;
    }
}


