package com.phoenix.howabouttoday.member.wishlist.service;

import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.repository.AccommodationRepository;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import com.phoenix.howabouttoday.member.wishlist.controller.WishListDto;
import com.phoenix.howabouttoday.member.wishlist.domain.WishList;
import com.phoenix.howabouttoday.member.wishlist.domain.WishlistRepository;
import com.phoenix.howabouttoday.room.repository.RoomRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class WishListServiceImpl implements WishListService{



    private final MemberRepository memberRepository;
    private final WishlistRepository wishlistRepository;
    private final AccommodationRepository accommodationRepository;

    /** memberNum에 해당하는 페이징리스트 반환 **/
    @Override
    public Page<WishListDto.ResponseDto> findByMemberNum(Pageable pageable,Long memberNum) {
        memberRepository.findById(memberNum).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));

        /*memberNum에 해당하는 페이지객체 반환 */
        Page<WishList> pageWishList = wishlistRepository.findAllByMemberMemberNum(memberNum,pageable);

        /* Dto로 변환 */
        Page<WishListDto.ResponseDto> wishListPageList = pageWishList.map(
                wishlist -> new WishListDto.ResponseDto(wishlist)
        );

        return wishListPageList;

    }
    /** memberNum에 해당하는 찜 리스트 존재 여부 확인 -유저의 찜 목록이 존재하는지 확인 **/
    @Override
    public boolean checkHaveWish(Long memberNum) {
        return wishlistRepository.existsByMember_MemberNum(memberNum);
    }

    /** memberNum이 AccomNum을 찜 리스트에 추가했는지 확인 -유저가 특정 숙소를 찜했는지 확인 **/
    @Override
    public boolean checkWish(Long memberNum, Long accomNum) {
        return wishlistRepository.existsByMember_MemberNumAndAccommodation_AccomNum(memberNum,accomNum);
    }

    /** 찜 목록에 숙소 추가 **/
    @Override
    public void save(Long memberNum, Long accomNum) {

        Member member = memberRepository.findById(memberNum).orElseThrow(() ->
               new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));

        Accommodation accommodation = accommodationRepository.findById(accomNum).orElseThrow(() ->
                new IllegalArgumentException("해당 숙소가 존재하지 않습니다"));

        wishlistRepository.save(WishList.builder().member(member).accommodation(accommodation).build());

    }

    /** 위시리스트에서 memberNum, accomNum에 해당하는 찜 삭제 - 유저가 특정 숙소 취소 **/
    @Override
    public void deleteByNum(Long memberNum, Long accomNum) {
        wishlistRepository.deleteByMember_MemberNumAndAccommodation_AccomNum(memberNum,accomNum);
    }

    /**찜 목록에서 reserveNum에 해당하는 찜 삭제 -유저가 특정 숙소 찜 취소 **/
    @Override
    public void deleteByWish(Long wishListNum) {
        WishList wish = wishlistRepository.findById(wishListNum).orElseThrow(() ->
                new IllegalArgumentException("해당 찜이 존재하지 않습니다."));

        wishlistRepository.delete(wish);
    }

//    @Override
//    public PageVo getPageInfo(Page<WishListDto.ResponseDto> wishPageList, int pageNo) {
//        int totalPage = wishPageList.getTotalPages();
//
//        // 현재 페이지를 통해 현재 페이지 그룹의 시작 페이지를 구함
//        int startNumber = (int)((Math.floor(pageNo/PAGE_WISHLIST_COUNT)*PAGE_WISHLIST_COUNT)+1 <= totalPage ? (Math.floor(pageNo/PAGE_WISHLIST_COUNT)*PAGE_WISHLIST_COUNT)+1 : totalPage);
//
//        // 전체 페이지 수와 현재 페이지 그룹의 시작 페이지를 통해 현재 페이지 그룹의 마지막 페이지를 구함
//        int endNumber = (startNumber + PAGE_WISHLIST_COUNT-1 < totalPage ? startNumber + PAGE_WISHLIST_COUNT-1 : totalPage);
//        boolean hasPrev = wishPageList.hasPrevious();
//        boolean hasNext = wishPageList.hasNext();
//
//        /* 화면에는 원래 페이지 인덱스+1 로 출력됨을 주의 */
//        int prevIndex = wishPageList.previousOrFirstPageable().getPageNumber()+1;
//        int nextIndex = wishPageList.nextOrLastPageable().getPageNumber()+1;
//
//        return new PageVo(totalPage, startNumber, endNumber, hasPrev, hasNext, prevIndex, nextIndex);
//
//    }
}