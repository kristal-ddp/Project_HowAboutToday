package com.phoenix.howabouttoday.member.wishlist.controller;

import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.member.wishlist.domain.WishList;
import com.phoenix.howabouttoday.member.wishlist.domain.WishlistRepository;
import com.phoenix.howabouttoday.member.wishlist.service.PageVo;
import com.phoenix.howabouttoday.member.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user-dashboard-wishlist")
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    /** 내 찜 목록 반환 **/
    @GetMapping
    public String readWishList(@LoginUser SessionDTO user,
                               @PageableDefault(page = 0,size = 10,sort = "reserveNum",direction = Sort.Direction.DESC)  Pageable pageable,
                               Model model) {



        /** 회원 조회 로직 **/
        Long memberNum = user.getMemberNum();

        /* 내가 찜한 목록이 존재하는지 확인 */
        boolean checkWish = wishListService.checkHaveWish(memberNum);

        if(checkWish){

            Page<WishListDto.ResponseDto> wishPageList = wishListService.findByMemberNum(pageable, memberNum);

            /* 현재페이지,시작페이지,마지막페이지를 구하는 로직 */
            int nowPage = wishPageList.getPageable().getPageNumber() + 1;
            int startPage = Math.max(nowPage - 4, 1);
            int endPage = Math.min(nowPage + 4,wishPageList.getTotalPages());

            model.addAttribute("wishList",wishPageList);
            model.addAttribute("nowPage",nowPage);
            model.addAttribute("startPage",startPage);
            model.addAttribute("endPage", endPage);
        }

        model.addAttribute("checkWish",checkWish);

        /*헤더에 필요한 sessionDTO반환 */
        model.addAttribute("sessionDTO",user);

        return "member/userdashboard/user-dashboard-wishlist";
    }
    @PostMapping
    public String postWishList() {
        return "member/userdashboard/user-dashboard-wishlist";
    }


}
