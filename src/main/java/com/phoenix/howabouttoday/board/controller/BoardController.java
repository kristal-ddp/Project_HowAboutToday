package com.phoenix.howabouttoday.board.controller;

import com.phoenix.howabouttoday.board.dto.*;
import com.phoenix.howabouttoday.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 공지사항 리스트 페이지
    @GetMapping("notice")
    public String noticeList(Model model){

        List<BoardListDTO> boardList = boardService.findAll_Board("공지사항"); // boardCategoryName = "공지사항"인 데이터들을 DTO에 저장
        model.addAttribute("lists", boardList);

        return "board/board";
    }

    // 공지사항 디테일 페이지
    @GetMapping("notice/{boardNum}")
    public String noticeDetails(@PathVariable Long boardNum, Model model){

        BoardDetailDTO boardDetailDTO = boardService.findOne_Board(boardNum);
        model.addAttribute("boardDetailDTO", boardDetailDTO);

        return "board/notice-details";
    }

    // 이벤트 리스트 페이지
    @GetMapping("event")
    public String eventList(Model model){

        List<EventListDTO> eventList = boardService.findAll_Event();
        model.addAttribute("lists", eventList);

        return "board/board";
    }

    // 이벤트 디테일 페이지
    @GetMapping("event/{eventNum}")
    public String eventDetails(@PathVariable Long eventNum, Model model){

        EventDetailDTO eventDetailDTO = boardService.findOne_Event(eventNum);
        model.addAttribute("eventDetailDTO", eventDetailDTO);

        return "board/event-details";
    }

    // FAQ 리스트 페이지
    @GetMapping("faq")
    public String faqList(Model model){

        List<List<BoardDetailDTO>> faqList = boardService.findAll_FAQ("FAQ"); // boardCategoryName = "FAQ"인 데이터들을 DTO에 저장
        model.addAttribute("lists", faqList);

        return "board/faq";
    }

    // 고객센터
    @GetMapping("contact")
    public String getContact(){
        return "board/contact";
    }
    @PostMapping("contact")
    public String postContact(){
        return "board/contact";
    }

    // 오늘어때 정보 리스트 페이지
    @GetMapping("aboutUs")
    public String aboutUsList(Model model){

        List<BoardListDTO> boardList = boardService.findAll_Board("오늘어때 정보"); // boardCategoryName = "오늘어때 정보"인 데이터들을 DTO에 저장
        model.addAttribute("lists", boardList);

        return "board/aboutUs";
    }

    // 오늘어때 정보 디테일 페이지
    @GetMapping("aboutUs/{boardNum}")
    public String aboutUsDetails(@PathVariable Long boardNum, Model model){

        BoardDetailDTO boardDetailDTO = boardService.findOne_Board(boardNum);
        model.addAttribute("boardDetailDTO", boardDetailDTO);

        return "board/aboutUs-details";
    }

}
