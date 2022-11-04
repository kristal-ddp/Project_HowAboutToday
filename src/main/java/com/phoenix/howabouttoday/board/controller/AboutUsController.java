package com.phoenix.howabouttoday.board.controller;

import com.phoenix.howabouttoday.board.dto.*;
import com.phoenix.howabouttoday.board.service.BoardService;
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

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AboutUsController {

    private final BoardService boardService;

    // 오늘어때 정보 리스트 페이지
    @GetMapping("aboutUs")
    public String aboutUsList(@LoginUser SessionDTO sessionDTO, Model model,
                              @PageableDefault Pageable pageable, MemberDTO memberDTO){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        pageable = PageRequest.of(0, 3, Sort.Direction.DESC, "board_num");
        Slice<BoardListDTO> boardList = boardService.findAll_Board("오늘어때 정보", pageable); // boardCategoryName = "오늘어때 정보"인 데이터들을 DTO에 저장

        model.addAttribute("lists", boardList);

        return "board/aboutUs";
    }

    // 오늘어때 정보 리스트 더보기
    @ResponseBody
    @GetMapping("aboutUs-more")
    public Slice<BoardListDTO> aboutUsList(@PageableDefault Pageable pageable){

        pageable = PageRequest.of(pageable.getPageNumber(), 3, Sort.Direction.DESC, "board_num");
        Slice<BoardListDTO> boardList = boardService.findAll_Board("오늘어때 정보", pageable);

        return boardList;
    }

    // 오늘어때 정보 디테일 페이지
    @GetMapping("aboutUs/{boardNum}")
    public String aboutUsDetails(@LoginUser SessionDTO sessionDTO, Model model,
                                 @PathVariable Long boardNum, MemberDTO memberDTO){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        BoardDetailDTO boardDetailDTO = boardService.findOne_Board(boardNum);
        model.addAttribute("boardDetailDTO", boardDetailDTO);

        return "board/aboutUs-details";
    }

    // 오늘어때 정보 작성 페이지
    @GetMapping("admin/aboutUs-add")
    public String aboutUsAdd(@ModelAttribute("boardDTO") BoardDTO boardDTO,
                             @LoginUser SessionDTO sessionDTO, Model model, MemberDTO memberDTO){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        boardDTO.setMemberNum(sessionDTO.getMemberNum());

        return "board/aboutUs-add";
    }

    // 오늘어때 정보 작성
    @PostMapping("admin/aboutUs-add")
    public String aboutUsAdd(@Valid BoardDTO boardDTO, BindingResult bindingResult,
                             @LoginUser SessionDTO sessionDTO, Model model, MemberDTO memberDTO){

        if(bindingResult.hasErrors()) {
            model.addAttribute("sessionDTO", sessionDTO);
            return "board/aboutUs-add";
        }

        boardDTO.setBoardCategoryNum(2L);
        boardService.addBoard(boardDTO);

        return "redirect:/aboutUs";
    }

    // 오늘어때 정보 수정 페이지
    @GetMapping("admin/aboutUs-edit/{boardNum}")
    public String aboutUsEdit(@PathVariable Long boardNum, Model model,
                              @LoginUser SessionDTO sessionDTO, MemberDTO memberDTO){

        if(sessionDTO == null) {
            return "/loginProc";
        }

        BoardDetailDTO boardDetailDTO = boardService.findOne_Board(boardNum);
        model.addAttribute("boardDetailDTO", boardDetailDTO);
        model.addAttribute("sessionDTO", sessionDTO);

        return "board/aboutUs-edit";
    }

    // 오늘어때 정보 수정
    @PostMapping("admin/aboutUs-edit/{boardNum}")
    public String aboutUsEdit(@PathVariable Long boardNum, @Valid BoardDTO boardDTO, BindingResult bindingResult,
                              @LoginUser SessionDTO sessionDTO, Model model, MemberDTO memberDTO){

        if(bindingResult.hasErrors()) {

            if(sessionDTO == null) {
                return "/loginProc";
            }

            BoardDetailDTO boardDetailDTO = boardService.findOne_Board(boardNum);
            model.addAttribute("boardDetailDTO", boardDetailDTO);
            model.addAttribute("sessionDTO", sessionDTO);

            return "board/notice-edit";
        }

        boardService.editBoard(boardNum, boardDTO);

        return "redirect:/aboutUs/{boardNum}";
    }

    // 오늘어때 정보 삭제
    @GetMapping("admin/aboutUs-delete/{boardNum}")
    public String aboutUsDelete(@PathVariable Long boardNum) {

        BoardDetailDTO boardDetailDTO = boardService.findOne_Board(boardNum);
        boardService.deleteBoard(boardDetailDTO);

        return "redirect:/aboutUs";
    }

}
