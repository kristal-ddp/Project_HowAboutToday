package com.phoenix.howabouttoday.room.controller;

import com.phoenix.howabouttoday.room.dto.*;
import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("room-details")
    public String getRoomDetails(@LoginUser SessionDTO sessionDTO, Model model, Long roomNum){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        roomNum = 1L;

        List<RoomImageDTO> iList = roomService.findAll_Image(roomNum);
        model.addAttribute("ilist",iList); //객실 이미지

        List<RoomAmenitiesDTO> aList = roomService.findAll_Amenities(roomNum);
        model.addAttribute("alist",aList); //객실 시설 리스트

        List<RoomServiceDTO> sList = roomService.findAll_Service(roomNum);
        model.addAttribute("slist",sList); //객실 서비스 리스트

//        RoomDetailDTO roomDetailDTO = roomService.findOne_Room(roomNum);
        RoomDetailDTO roomDetailDTO = roomService.findOne_Room(roomNum);


        model.addAttribute("roomDetailDTO",roomDetailDTO); //객실 디테일

        return "accom/room/room-details";

    }
    @PostMapping("room-details")
    public String postRoomDetails(){
        return "accom/room/room-details";
    }

}
