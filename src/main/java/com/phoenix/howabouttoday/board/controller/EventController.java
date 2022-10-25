package com.phoenix.howabouttoday.board.controller;

import com.phoenix.howabouttoday.board.dto.*;
import com.phoenix.howabouttoday.board.service.EventService;
import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    // 이벤트 리스트 페이지
    @GetMapping("event")
    public String eventList(@LoginUser SessionDTO sessionDTO, Model model){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        List<EventListDTO> eventList = eventService.findAll_Event();
        model.addAttribute("lists", eventList);

        return "board/board";
    }

    // 이벤트 디테일 페이지
    @GetMapping("event/{eventNum}")
    public String eventDetails(@LoginUser SessionDTO sessionDTO, @PathVariable Long eventNum, Model model){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        EventDetailDTO eventDetailDTO = eventService.findOne_Event(eventNum);
        model.addAttribute("eventDetailDTO", eventDetailDTO);

        return "board/event-details";
    }

    // 이벤트 작성 페이지
    @GetMapping("admin/event-add")
    public String eventAdd(@ModelAttribute("eventAddDTO") EventAddDTO eventAddDTO,
                           @LoginUser SessionDTO sessionDTO, Model model){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        eventAddDTO.setMemberNum(sessionDTO.getMemberNum());

        return "board/event-add";
    }

    // 이벤트 작성
    @PostMapping("admin/event-add")
    public String eventAdd(@Valid EventAddDTO eventAddDTO, BindingResult bindingResult,
                           @LoginUser SessionDTO sessionDTO, Model model,
                           @RequestParam("eventImageList") List<MultipartFile> eventImageList) throws Exception {

        if(bindingResult.hasErrors()) {
            model.addAttribute("sessionDTO", sessionDTO);
            return "board/event-add";
        }

        eventService.addEvent(eventAddDTO, eventImageList);

        return "redirect:/event";
    }

}
