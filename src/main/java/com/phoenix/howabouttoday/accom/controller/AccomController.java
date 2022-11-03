package com.phoenix.howabouttoday.accom.controller;


import com.phoenix.howabouttoday.accom.dto.AccommodationDTO;
import com.phoenix.howabouttoday.accom.dto.SearchForm;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.entity.Facilities;
import com.phoenix.howabouttoday.accom.dto.AccomCategoryDto;
import com.phoenix.howabouttoday.accom.dto.AccomDto;
import com.phoenix.howabouttoday.accom.dto.AccomReviewDTO;
import com.phoenix.howabouttoday.accom.entity.AccomImage;

import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.entity.Facilities;
import com.phoenix.howabouttoday.accom.entity.Region;
import com.phoenix.howabouttoday.accom.service.*;

//import com.phoenix.howabouttoday.payment.AccomCategory;


import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.room.dto.RoomImageDTO;
import com.phoenix.howabouttoday.room.dto.RoomListDTO;
import com.phoenix.howabouttoday.room.service.RoomService;


import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccomController {

    private final AccomodationService accommodationService;
    private final RoomService roomService;
    private final FacilitiesService facilitiesService;
    private final AccomReviewService accomReviewService;
    private final AccomCategoryService accomCategoryService;
    private final RegionService regionService;

    // 메인 화면
    @GetMapping(value = {"/", "home"})
    public String home(@LoginUser SessionDTO sessionDTO, Model model){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        /**화면에 표시할 숙소카테고리 조회 **/
        List<AccomCategoryDto.ResponseDto> accomCategorys = accomCategoryService.findAccomList();
        model.addAttribute("accomCategorys",accomCategorys);

        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("memberDTO",memberDTO);

        boolean memberCheck = false;
        boolean loginCheck = false;
        model.addAttribute("memberCheck",memberCheck);

        SearchForm searchForm = new SearchForm();
        model.addAttribute("searchForm",searchForm);
        model.addAttribute("loginCheck",loginCheck);
        return "home";
    }

    // 숙소유형별 리스트 출력
    @GetMapping("/accom/{category_name}")
    public String readAccom(@PathVariable(required = false) String category_name,
                            @PageableDefault(page = 0,size = 5,sort = "accomNum",direction = Sort.Direction.DESC) Pageable pageable,
                            @LoginUser SessionDTO sessionDTO,
                            SearchForm searchForm,
                            Model model) {

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }



        /** 화면에 표시할 한글카테고리 이름 조회**/
        String viewName = accomCategoryService.getAccomViewName(category_name);

        /** 화면에 표시할 부모 지역 조회 **/
        List<Region> parentRegions = regionService.findAllParent();


        /** 회원 Object 반환하는 로직 **/
        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("memberDTO",memberDTO);
        boolean memberCheck = false;
        model.addAttribute("memberCheck",memberCheck);
//        model.addAttribute("categoryList",categoryList);
        model.addAttribute("viewName",viewName);
        model.addAttribute("categoryName",category_name);
        model.addAttribute("sessionDTO", sessionDTO);
        model.addAttribute("parentRegions", parentRegions);

        /** 검색한 날짜와 인원수 폼 반환 **/
        model.addAttribute("searchForm",searchForm);

        return "accom/hotel/hotel-list";

    }

    //숙소 상세
    @GetMapping("hotel-single")
    public String getHotelSingle(@LoginUser SessionDTO sessionDTO, Model model,
                                 @RequestParam Long accomNum,
                                 SearchForm searchForm){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        /** 숙소반환 로직 **/
        AccommodationDTO findAccom = accommodationService.findByAccomNum(accomNum, searchForm);
        model.addAttribute("accom",findAccom);

        /** 해당 숙소의 숙소시설 리스트  **/

        /** 회원 Object 반환하는 로직 **/
        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("memberDTO",memberDTO);
        boolean memberCheck = false;
        model.addAttribute("memberCheck",memberCheck);

        /** searchForm 반환 **/
        model.addAttribute("searchForm",searchForm);

        List<AccomReviewDTO.ResponseDto> reviewlist = accomReviewService.findAllByAccom(accomNum);
        for (AccomReviewDTO.ResponseDto responseDto : reviewlist) {
            System.out.println("responseDto.getAccomReviewRating() = " + responseDto.getAccomReviewRating());
        }
        model.addAttribute("reviewlist",reviewlist);//리뷰 리스트 출력
        return "accom/hotel/hotel-single";
    }
    @PostMapping("hotel-single")
    public String postHotelSingle(){return "accom/hotel/hotel-single";}

    @GetMapping("singleSearch")
    public String getHotelSingleSearch(@LoginUser SessionDTO sessionDTO, Model model,Long accomNum) {

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }


        System.out.println("accomNum!!!!!!!!!!!!! = " + accomNum);
        Accommodation accomList= accommodationService.findAccom(accomNum);//숙소 정보
        List<RoomListDTO> roomList = roomService.findAll_Room(accomNum);

        model.addAttribute("roomlist",roomList); //객실 리스트
        model.addAttribute("accommodation",accomList);

        return "accom/hotel/hotel-single";
    }




}
