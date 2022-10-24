package com.phoenix.howabouttoday.member.wishlist.controller;

import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.member.wishlist.domain.WishlistRepository;
import com.phoenix.howabouttoday.member.wishlist.service.WishListService;
import com.phoenix.howabouttoday.member.wishlist.service.WishListServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/wish")
public class WishListRestController {

    private final WishListService wishListService;

    /** 위시리스트에 숙소 추가 **/
    @PostMapping("/{accomNum}")
    public boolean save(@LoginUser SessionDTO user,
                        @PathVariable Long accomNum){

        /** 세션회원 조회로직 **/
        Long memberNum = user.getMemberNum();


        if(wishListService.checkWish(memberNum, accomNum)){
            /* 해당 숙소를 이미 찜했다면 찜 목록에서 삭제 */
            wishListService.deleteByNum(memberNum,accomNum);
            return true;
        }else {
            /* 해당 숙소를 찜하지 않았다면 찜 목록에 숙소 추가 */
            wishListService.save(memberNum,accomNum);
            return false;
        }
    }

    /** 위시리스트에서 숙소 삭제 **/
    @DeleteMapping("/{wishNum}")
    public ResponseEntity delete(@PathVariable Long wishNum){


        wishListService.deleteByWish(wishNum);

        return new ResponseEntity(HttpStatus.OK);
    }
}
