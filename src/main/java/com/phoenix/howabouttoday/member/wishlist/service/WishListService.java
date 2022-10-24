package com.phoenix.howabouttoday.member.wishlist.service;

import com.phoenix.howabouttoday.member.wishlist.controller.WishListDto;
import com.phoenix.howabouttoday.member.wishlist.domain.WishList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WishListService {

    /** 찜 리스트 반환 **/
    Page<WishListDto.ResponseDto> findByMemberNum(Pageable pageable,Long memberNum);

    /** memberNum에 해당하는 찜 리스트 존재 여부 확인 - 유저의 찜 목록이 존재하는지 확인 **/
    boolean checkHaveWish(Long memberNum);

    /** memberNum이 AccomNum을 찜 리스트에 추가했는지 확인 - 유저가 특정 숙소를 찜했는지 확인 **/
    boolean checkWish(Long memberNum, Long accomNum);

    /**찜 목록에 숙소 추가 **/
    void save(Long memberNum, Long accomNum);

    /** memberNum, AccomNum에 해당하는 찜 삭제 - 유저가 특정 숙소 찜 취소 **/
    void deleteByNum(Long memberNum, Long accomNum);

    /** 찜 목록에서 wishNum에 해당하는 찜 삭제 -유저가 특정 숙소 찜 취소 - **/
    void deleteByWish(Long wishListNum);

    /** 페이징 정보 반환 **/
//    PageVo getPageInfo(Page<WishListDto.ResponseDto> wishPageList, int pageNo);

}
