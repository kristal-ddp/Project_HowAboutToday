package com.phoenix.howabouttoday.board.controller;

import com.phoenix.howabouttoday.board.dto.BoardDetailDTO;
import com.phoenix.howabouttoday.board.dto.BoardListDTO;
import com.phoenix.howabouttoday.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 공지사항
    // 리스트 페이지
    @GetMapping("notice")
    public List<BoardListDTO> noticeList(){
        return boardService.boardList();
    }
    @PostMapping("notice")
    public String postNotice(){
        return "board/board";
    }

    // 디테일 페이지
    @GetMapping("notice/{boardNum}")
    public BoardDetailDTO noticeDetail(@PathVariable Long boardNum){
        return boardService.boardDetail(boardNum);
    }
    @PostMapping("notice-details")
    public String postNoticeDetails(){
        return "board/board-details";
    }

    // 이벤트
    // 리스트 페이지
    @GetMapping("event")
    public String getEvent(){
        return "board/board";
    }
    @PostMapping("event")
    public String postEvent(){
        return "board/board";
    }

    // 디테일 페이지
    @GetMapping("event-details")
    public String getEventDetails(){
        return "board/board-details";
    }
    @PostMapping("event-details")
    public String postEventDetails(){
        return "board/board-details";
    }

    // 자주 묻는 질문
    // 리스트 페이지
    @GetMapping("faq")
    public String getFaq(){
        return "board/faq";
    }
    @PostMapping("faq")
    public String postFaq(){
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

    // 오늘어때 정보
    // 리스트 페이지
    @GetMapping("about")
    public String getAbout(){
        return "board/about";
    }
    @PostMapping("about")
    public String postAbout(){
        return "board/about";
    }

    // 디테일 페이지
    @GetMapping("about-details")
    public String getAboutDetails(){
        return "board/board-details";
    }
    @PostMapping("about-details")
    public String postAboutDetails(){
        return "board/board-details";
    }

}
