package com.phoenix.howabouttoday.accom.controller;

import com.phoenix.howabouttoday.accom.dto.AccomCategoryDto;
import com.phoenix.howabouttoday.accom.dto.AccomDto;
import com.phoenix.howabouttoday.accom.entity.AccomImage;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.entity.Facilities;
import com.phoenix.howabouttoday.accom.entity.Facility;
import com.phoenix.howabouttoday.accom.service.AccomCategoryService;
import com.phoenix.howabouttoday.accom.service.AccomodationService;

//import com.phoenix.howabouttoday.payment.AccomCategory;


import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.room.dto.RoomImageDTO;
import com.phoenix.howabouttoday.room.dto.RoomListDTO;
import com.phoenix.howabouttoday.accom.service.FacilitiesService;
import com.phoenix.howabouttoday.room.service.RoomService;

import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class AccomController {

    private final AccomodationService accommodationService;
    private final RoomService roomService;
    private final FacilitiesService facilitiesService;

    private final AccomCategoryService accomCategoryService;


//    public AccomController(AccomodationService accomodationService, RoomService roomService) {
//        this.accomodationService = accomodationService;
//        this.roomService = roomService;
//    }

    // 메인 화면
    @GetMapping(value = {"/", "home"})
    public String home(@LoginUser SessionDTO sessionDTO, Model model){
    //가나다라
        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        return "home";
    }

    // 숙소유형별 리스트 출력
    @GetMapping("/accom/{category_name}")
    public String readAccom(@PathVariable(required = false) String category_name,
                            @PageableDefault(page = 0,size = 5,sort = "accomNum",direction = Sort.Direction.DESC) Pageable pageable,
                            @LoginUser SessionDTO sessionDTO,
                            Model model) {

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }
        
        System.out.println("카테고리호출!!!! = " + category_name);
        List<AccomCategoryDto.ResponseDto> categoryList = accomCategoryService.findAccomList();

        model.addAttribute("categoryList",categoryList);
        String viewName = accomCategoryService.getAccomViewName(category_name);
        model.addAttribute("viewName",viewName);
        Slice<AccomDto.ResponsePageDto> accomPageList = accommodationService.getAccomPageList(pageable,category_name);
        int size = accomPageList.getSize();

        model.addAttribute("size",size);
        model.addAttribute("categoryName",category_name);
        model.addAttribute("accomPageList",accomPageList);
        model.addAttribute("sessionDTO", sessionDTO);

        return "accom/hotel/hotel-list";

    }
    @GetMapping("hotel-list")
    public String getHotelList(@LoginUser SessionDTO sessionDTO, Model model){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        List<Accommodation> accommodationList = accommodationService.getAccommodationlist();

        model.addAttribute("accommodationList",accommodationList);

        return "accom/hotel/hotel-list";
    }

    @PostMapping("hotel-list")
    public String postHotelList(){
        return "accom/hotel/hotel-list";
    }

    @GetMapping("hotel-listSearch")
    public String getHotelSearchResult(@RequestParam(value = "keyword") String keyword,
                                       @LoginUser SessionDTO sessionDTO, Model model, String date){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        List<Accommodation> accommodationList = accommodationService.searchResults(keyword);

        model.addAttribute("accommodationList", accommodationList);
        return "accom/hotel/hotel-list";
    }

//    @PostMapping("hotel-search-result")
//    public String postHotelSearchResult(){
//        return "accom/hotel/hotel-search-result";
//    }

    //숙소 상세
    @GetMapping("hotel-single")
    public String getHotelSingle(@LoginUser SessionDTO sessionDTO, Model model,Long accomNum,Long roomNum){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        List<RoomImageDTO> iList = roomService.findAll_Image(roomNum);
        model.addAttribute("ilist",iList); //객실 이미지 리스트

        List<RoomListDTO> roomList = roomService.findAll_Room(accomNum);
        model.addAttribute("roomlist",roomList); //객실 리스트

        Accommodation accomList= accommodationService.findAccom(accomNum);//숙소 정보
        List<Facilities> facilitiesList = facilitiesService.getFacilitiesList();//숙소 시설
        model.addAttribute("facilities",facilitiesList);
        model.addAttribute("accommodation",accomList);
//        model.addAttribute("roomlist", roomService.roomList());
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

//    @GetMapping("motel-list")
//    public String getMotelList(@LoginUser SessionDTO sessionDTO, Model model){
//
//        if(sessionDTO != null) {
//            model.addAttribute("sessionDTO", sessionDTO);
//        }
//
//        return "accom/hotel/motel-list";
//    }
//    @PostMapping("motel-list")
//    public String postMotelList(){
//        return "accom/hotel/motel-list";
//    }
//
//    @GetMapping("motel-search-result")
//    public String getMotelSearchResult(@LoginUser SessionDTO sessionDTO, Model model){
//
//        if(sessionDTO != null) {
//            model.addAttribute("sessionDTO", sessionDTO);
//        }
//
//        return "accom/hotel/motel-search-result";
//    }
//    @PostMapping("motel-search-result")
//    public String postMotelSearchResult(){
//        return "accom/hotel/motel-search-result";
//    }
//
//    @GetMapping("motel-single")
//    public String getMotelSingle(@LoginUser SessionDTO sessionDTO, Model model,Long accomNum){
//
//        if(sessionDTO != null) {
//            model.addAttribute("sessionDTO", sessionDTO);
//        }
//
//        return "accom/hotel/motel-single";
//
//    }
//    @PostMapping("motel-single")
//    public String postMotelSingle(){
//        return "accom/hotel/motel-single";
//    }
//
//    @GetMapping("pension-PoolVilla-list")
//    public String getPensionPoolVillaList(@LoginUser SessionDTO sessionDTO, Model model){
//
//        if(sessionDTO != null) {
//            model.addAttribute("sessionDTO", sessionDTO);
//        }
//
//        return "accom/hotel/pension-PoolVilla-list";
//    }
//    @PostMapping("pension-PoolVilla-list")
//    public String postPensionPoolVillaList(){
//        return "accom/hotel/pension-PoolVilla-list";
//    }
//
//    @GetMapping("pension-PoolVilla-single")
//    public String getPensionPoolVillaSingle(@LoginUser SessionDTO sessionDTO, Model model,Long accomNum){
//
//        if(sessionDTO != null) {
//            model.addAttribute("sessionDTO", sessionDTO);
//        }
//
//        List<RoomListDTO> roomList = roomService.findAll_Room(accomNum);
//        model.addAttribute("roomlist",roomList);
//        return "accom/hotel/pension-PoolVilla-single";
//
//    }
//    @PostMapping("pension-PoolVilla-single")
//    public String postPensionPoolVillaSingle(){
//        return "accom/hotel/pension-PoolVilla-single";
//    }
//
//    @GetMapping("pension-PoolVilla-result")
//    public String getPensionPoolVillaResult(@LoginUser SessionDTO sessionDTO, Model model){
//
//        if(sessionDTO != null) {
//            model.addAttribute("sessionDTO", sessionDTO);
//        }
//
//        return "accom/hotel/pension-PoolVilla-result";
//    }
//    @PostMapping("pension-PoolVilla-result")
//    public String postPensionPoolVillaResult(){
//        return "accom/hotel/pension-PoolVilla-result";
//    }
//
//    @GetMapping("guestHouse-Hanok-list")
//    public String getGuestHouseList(@LoginUser SessionDTO sessionDTO, Model model){
//
//        if(sessionDTO != null) {
//            model.addAttribute("sessionDTO", sessionDTO);
//        }
//
//        return "accom/hotel/guestHouse-Hanok-list";
//    }
//    @PostMapping("guestHouse-Hanok-list")
//    public String postGuestHouseList(){ return "accom/hotel/guestHouse-Hanok-list";}
//
//    @GetMapping("guestHouse-Hanok-result")
//    public String getGuestHouseSingle(@LoginUser SessionDTO sessionDTO, Model model){
//
//        if(sessionDTO != null) {
//            model.addAttribute("sessionDTO", sessionDTO);
//        }
//
//        return "accom/hotel/guestHouse-Hanok-result";
//    }
//    @PostMapping("guesthouse-Hanok-result")
//    public String postGuestHouseSingle(){ return "accom/hotel/guestHouse-Hanok-result";}
//
//    @GetMapping("guestHouse-Hanok-single")
//    public String getGuestHouseResult(@LoginUser SessionDTO sessionDTO, Model model,Long accomNum){
//
//        if(sessionDTO != null) {
//            model.addAttribute("sessionDTO", sessionDTO);
//        }
//
//        List<RoomListDTO> roomList = roomService.findAll_Room(accomNum);
//        model.addAttribute("roomlist",roomList);
//        return "accom/hotel/guestHouse-Hanok-single";
//
//    }
//    @PostMapping("guestHouse-Hanok-single")
//    public String postGuestHouseResult(){ return "accom/hotel/guestHouse-Hanok-single";}


}
