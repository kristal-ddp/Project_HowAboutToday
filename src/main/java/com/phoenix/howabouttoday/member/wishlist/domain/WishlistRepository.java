package com.phoenix.howabouttoday.member.wishlist.domain;


import com.phoenix.howabouttoday.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<WishList,Long>{

    /** memberNum에 해당하는 찜 리스트가 존재하는지 확인 - 유저의 찜 목록 여부 **/
    boolean existsByMember_MemberNum(Long memberNum);

    /** memberNum, accomNum에 해당하는 wish 엔티티 존재여부 반환 -유저가 특정 숙소를 찜 목록에 추가했는지 확인 **/
    boolean existsByMember_MemberNumAndAccommodation_AccomNum(Long memberNum, Long AccomNum);

    /** memeberNum, accomNum에 해당하는 찜 엔티티 삭제 -유저가 특정 객실 삭제 - **/
    void deleteByMember_MemberNumAndAccommodation_AccomNum(Long memberNum, Long AccomNum);

    /** memberNum으로 찜리스트를 정렬해서 페이징해오기 -페이징 된 리스트 반환 - */
    Page<WishList> findAllByMemberMemberNum(Long memberNum,Pageable pageable);

}