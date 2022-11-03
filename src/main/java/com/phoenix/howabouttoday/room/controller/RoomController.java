package com.phoenix.howabouttoday.room.controller;

import com.phoenix.howabouttoday.accom.dto.SearchForm;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.payment.dto.OrdersDetailDTO;
import com.phoenix.howabouttoday.room.dto.*;
import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.room.service.ReviewService;
import com.phoenix.howabouttoday.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final ReviewService reviewService;

    @GetMapping("room-details")
    public String getRoomDetails(@LoginUser SessionDTO sessionDTO, Model model, @RequestParam(value="roomNum",required=false) Long roomNum,
                                 SearchForm searchForm){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        List<RoomImageDTO> iList = roomService.findAll_Image(roomNum);
        model.addAttribute("ilist",iList); //객실 이미지

        List<RoomAmenitiesDTO> aList = roomService.findAll_Amenities(roomNum);
        model.addAttribute("alist",aList); //객실 시설 리스트

        List<RoomServiceDTO> sList = roomService.findAll_Service(roomNum);
        model.addAttribute("slist",sList); //객실 서비스 리스트

        RoomDetailDTO roomDetailDTO = roomService.findOne_Room(roomNum);

        List<RoomReviewDTO> rList = roomService.findAll_Review(roomNum);
        model.addAttribute("rlist",rList); //객실 리뷰 리스트

        model.addAttribute("roomDetailDTO",roomDetailDTO); //객실 디테일

        /** 날짜와 성인 어린이값 반환 **/
        model.addAttribute("searchForm",searchForm);
        /** 회원정보 반환 **/
        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("memberDTO",memberDTO);

        boolean memberCheck = false;
        model.addAttribute("memberCheck",memberCheck);

        List<OrdersDetailDTO> ordersDetailDTOList = reviewService.isExistOrderDetail(sessionDTO, roomNum);
        model.addAttribute("ordersDetailDTOList",ordersDetailDTOList);

        return "accom/room/room-details";
    }
    @PostMapping("room-details")
    public String postRoomDetails(){
        return "accom/room/room-details";
    }

}
