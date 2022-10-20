package com.phoenix.howabouttoday.reserve.domain;

import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {

    /** memberNum에 해당하는 cart 존재 여부 반환 - 유저의 장바구니가 존재하는지 확인 **/
    boolean existsByMember_MemberNum(Long memberNum);

    /** memberNum, roomNum에 해당하는 cart 엔티티 존재 여부 반환 **/
    boolean existsByMember_MemberNumAndRoom_RoomNum(Long memberNum, Long roomNum);

    /** memberNum에 해당하는 엔티티 리스트 반환 - 유저의 장바구니 목록 반환 **/
    List<Cart> findByMember_MemberNum(Long memberNum);

    /** memberNum에 해당하는 엔티티 모두 삭제 -유저의 장바구니 목록 모두 삭제 **/
    void deleteAllByMember_MemberNum(Long memberNum);

    /** memberNum에 해당하는 모든 장바구니 조회 **/
    List<Cart> findAllByMember_MemberNum(Long memberNum);





}
