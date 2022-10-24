package com.phoenix.howabouttoday.board.controller;

import com.phoenix.howabouttoday.board.dto.*;
import com.phoenix.howabouttoday.board.service.BoardService;
import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final BoardService boardService;

    // 공지사항 리스트 페이지
    @GetMapping("notice")
    public String noticeList(@LoginUser SessionDTO sessionDTO, Model model){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        List<BoardListDTO> boardList = boardService.findAll_Board("공지사항"); // boardCategoryName = "공지사항"인 데이터들을 DTO에 저장
        model.addAttribute("lists", boardList);

        return "board/board";
    }

    // 공지사항 디테일 페이지
    @GetMapping("notice/{boardNum}")
    public String noticeDetails(@LoginUser SessionDTO sessionDTO, @PathVariable Long boardNum, Model model){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        BoardDetailDTO boardDetailDTO = boardService.findOne_Board(boardNum);
        model.addAttribute("boardDetailDTO", boardDetailDTO);

        return "board/notice-details";
    }

    // 공지사항 작성 페이지
    @GetMapping("notice-add")
    public String noticeAdd(@ModelAttribute("boardAddDTO") BoardAddDTO boardAddDTO,
                            @LoginUser SessionDTO sessionDTO, Model model){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        boardAddDTO.setMemberNum(sessionDTO.getMemberNum());

        return "board/notice-add";
    }

    // 공지사항 작성
    @PostMapping("notice-add")
    public String noticeAdd(@Valid BoardAddDTO boardAddDTO, BindingResult bindingResult,
                            @LoginUser SessionDTO sessionDTO, Model model){

        if(bindingResult.hasErrors()) {
            model.addAttribute("sessionDTO", sessionDTO);
            return "board/notice-add";
        }

        boardAddDTO.setBoardCategoryNum(1L);
        boardService.addBoard(boardAddDTO);

        return "redirect:/notice";
    }

}
