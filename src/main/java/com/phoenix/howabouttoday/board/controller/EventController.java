package com.phoenix.howabouttoday.board.controller;

import com.phoenix.howabouttoday.board.dto.*;
import com.phoenix.howabouttoday.board.service.EventImageService;
import com.phoenix.howabouttoday.board.service.EventService;
import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private final EventImageService eventImageService;

    // 이벤트 리스트 페이지
    @GetMapping("event")
    public String eventList(@LoginUser SessionDTO sessionDTO, Model model,
                            Long eventNum, @PageableDefault Pageable pageable, MemberDTO memberDTO){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "eventNum");
        Slice<EventListDTO> eventList = eventService.findAll_Event(pageable);

        model.addAttribute("lists", eventList);

        return "board/event";
    }

    // 이벤트 리스트 더보기
    @ResponseBody
    @GetMapping("event-more")
    public Slice<EventListDTO> eventList(@PageableDefault Pageable pageable){

        pageable = PageRequest.of(pageable.getPageNumber(), 5, Sort.Direction.DESC, "eventNum");
        Slice<EventListDTO> eventList = eventService.findAll_Event(pageable);

        return eventList;
    }

    // 이벤트 디테일 페이지
    @GetMapping("event/{eventNum}")
    public String eventDetails(@PathVariable Long eventNum, Model model,
                               @LoginUser SessionDTO sessionDTO, MemberDTO memberDTO){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        EventDetailDTO eventDetailDTO = eventService.findOne_Event(eventNum);
        model.addAttribute("eventDetailDTO", eventDetailDTO);

        return "board/event-details";
    }

    // 이벤트 작성 페이지
    @GetMapping("admin/event-add")
    public String eventAdd(@ModelAttribute("eventDTO") EventDTO eventDTO,
                           @LoginUser SessionDTO sessionDTO, Model model, MemberDTO memberDTO){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        eventDTO.setMemberNum(sessionDTO.getMemberNum());

        return "board/event-add";
    }

    // 이벤트 작성
    @PostMapping("admin/event-add")
    public String eventAdd(@Valid EventDTO eventDTO, BindingResult bindingResult,
                           @RequestParam("eventImageList") List<MultipartFile> eventImageList,
                           @LoginUser SessionDTO sessionDTO, Model model, MemberDTO memberDTO) throws Exception {

        if(bindingResult.hasErrors()) {
            model.addAttribute("sessionDTO", sessionDTO);
            return "board/event-add";
        }

        eventService.addEvent(eventDTO, eventImageList);

        return "redirect:/event";
    }

    // 이벤트 수정 페이지
    @GetMapping("admin/event-edit/{eventNum}")
    public String eventEdit(@PathVariable Long eventNum, Model model,
                            @LoginUser SessionDTO sessionDTO, MemberDTO memberDTO){

        if(sessionDTO == null) {
            return "/loginProc";
        }

        EventDetailDTO eventDetailDTO = eventService.findOne_Event(eventNum);
        List<EventImageDTO> eventImageList = eventImageService.findAll_Image(eventNum);

        model.addAttribute("sessionDTO", sessionDTO);
        model.addAttribute("eventDetailDTO", eventDetailDTO);
        model.addAttribute("eventImageList", eventImageList);

        return "board/event-edit";
    }

    // 이벤트 수정
    @PostMapping("admin/event-edit/{eventNum}")
    public String eventEdit(@PathVariable Long eventNum, @Valid EventDTO eventDTO, BindingResult bindingResult,
                            @LoginUser SessionDTO sessionDTO, Model model, MemberDTO memberDTO) throws Exception {

        if(bindingResult.hasErrors()) {

            if(sessionDTO == null) {
                return "/loginProc";
            }

            EventDetailDTO eventDetailDTO = eventService.findOne_Event(eventNum);
            List<EventImageDTO> eventImageList = eventImageService.findAll_Image(eventNum);

            model.addAttribute("sessionDTO", sessionDTO);
            model.addAttribute("eventDetailDTO", eventDetailDTO);
            model.addAttribute("eventImageList", eventImageList);

            return "board/event-edit";
        }

        eventService.editEvent(eventNum, eventDTO);

        return "redirect:/event/{eventNum}";
    }

    // 이벤트 이미지 개별 삭제
    @GetMapping("admin/event-edit/{eventNum}/{eventImageNum}")
    public String eventImageDelete(@PathVariable("eventImageNum") Long eventImageNum, Long eventNum) {

        eventImageService.deleteImage(eventImageNum);
        return "redirect:/admin/event-edit/{eventNum}";
    }

    // 이벤트 삭제
    @GetMapping("admin/event-delete/{eventNum}")
    public String eventDelete(@PathVariable Long eventNum) {

        EventDetailDTO eventDetailDTO = eventService.findOne_Event(eventNum);
        eventService.deleteEvent(eventDetailDTO);

        return "redirect:/event";
    }

}
