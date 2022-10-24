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
public class AboutUsController {

    private final BoardService boardService;

    // 오늘어때 정보 리스트 페이지
    @GetMapping("aboutUs")
    public String aboutUsList(@LoginUser SessionDTO sessionDTO, Model model){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        List<BoardListDTO> boardList = boardService.findAll_Board("오늘어때 정보"); // boardCategoryName = "오늘어때 정보"인 데이터들을 DTO에 저장
        model.addAttribute("lists", boardList);

        return "board/aboutUs";
    }

    // 오늘어때 정보 디테일 페이지
    @GetMapping("aboutUs/{boardNum}")
    public String aboutUsDetails(@LoginUser SessionDTO sessionDTO, @PathVariable Long boardNum, Model model){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        BoardDetailDTO boardDetailDTO = boardService.findOne_Board(boardNum);
        model.addAttribute("boardDetailDTO", boardDetailDTO);

        return "board/aboutUs-details";
    }

    // 오늘어때 정보 작성 페이지
    @GetMapping("aboutUs-add")
    public String aboutUsAdd(@ModelAttribute("boardAddDTO") BoardAddDTO boardAddDTO,
                             @LoginUser SessionDTO sessionDTO, Model model){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        boardAddDTO.setMemberNum(sessionDTO.getMemberNum());

        return "board/aboutUs-add";
    }

    // 오늘어때 정보 작성
    @PostMapping("aboutUs-add")
    public String aboutUsAdd(@Valid BoardAddDTO boardAddDTO, BindingResult bindingResult,
                             @LoginUser SessionDTO sessionDTO, Model model){

        if(bindingResult.hasErrors()) {
            model.addAttribute("sessionDTO", sessionDTO);
            return "board/aboutUs-add";
        }

        boardAddDTO.setBoardCategoryNum(2L);
        boardService.addBoard(boardAddDTO);

        return "redirect:/aboutUs";
    }

}
