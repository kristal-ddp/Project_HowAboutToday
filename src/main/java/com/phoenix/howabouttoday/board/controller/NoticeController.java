package com.phoenix.howabouttoday.board.controller;

import com.phoenix.howabouttoday.board.dto.*;
import com.phoenix.howabouttoday.board.service.BoardService;
import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final BoardService boardService;

    // 공지사항 리스트 페이지
    @GetMapping("notice")
    public String noticeList(@LoginUser SessionDTO sessionDTO, Model model,
                             @PageableDefault Pageable pageable, MemberDTO memberDTO){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "board_num");
        Slice<BoardListDTO> boardList = boardService.findAll_Board("공지사항", pageable); // boardCategoryName = "공지사항"인 데이터들을 DTO에 저장

        model.addAttribute("lists", boardList);

        return "board/notice";
    }

    // 공지사항 리스트 더보기
    @ResponseBody
    @GetMapping("notice-more")
    public Slice<BoardListDTO> noticeList(@PageableDefault Pageable pageable){

        pageable = PageRequest.of(pageable.getPageNumber(), 5, Sort.Direction.DESC, "board_num");
        Slice<BoardListDTO> boardList = boardService.findAll_Board("공지사항", pageable);

        return boardList;
    }

    // 공지사항 디테일 페이지
    @GetMapping("notice/{boardNum}")
    public String noticeDetails(@PathVariable Long boardNum, Model model,
                                @LoginUser SessionDTO sessionDTO, MemberDTO memberDTO){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        BoardDetailDTO boardDetailDTO = boardService.findOne_Board(boardNum);
        model.addAttribute("boardDetailDTO", boardDetailDTO);

        return "board/notice-details";
    }

    // 공지사항 작성 페이지
    @GetMapping("admin/notice-add")
    public String noticeAdd(@ModelAttribute("boardDTO") BoardDTO boardDTO,
                            @LoginUser SessionDTO sessionDTO, Model model, MemberDTO memberDTO){

        if(sessionDTO == null) {
            return "/loginProc";
        }

        boardDTO.setMemberNum(sessionDTO.getMemberNum());
        model.addAttribute("sessionDTO", sessionDTO);

        return "board/notice-add";
    }

    // 공지사항 작성
    @PostMapping("admin/notice-add")
    public String noticeAdd(@Valid BoardDTO boardDTO, BindingResult bindingResult,
                            @LoginUser SessionDTO sessionDTO, Model model, MemberDTO memberDTO){

        if(bindingResult.hasErrors()) {

            if(sessionDTO == null) {
                return "/loginProc";
            }

            model.addAttribute("sessionDTO", sessionDTO);
            return "board/notice-add";
        }

        boardDTO.setBoardCategoryNum(1L);
        boardService.addBoard(boardDTO);

        return "redirect:/notice";
    }

    // 공지사항 수정 페이지
    @GetMapping("admin/notice-edit/{boardNum}")
    public String noticeEdit(@PathVariable Long boardNum, Model model,
                             @LoginUser SessionDTO sessionDTO, MemberDTO memberDTO){

        if(sessionDTO == null) {
            return "/loginProc";
        }

        BoardDetailDTO boardDetailDTO = boardService.findOne_Board(boardNum);
        model.addAttribute("boardDetailDTO", boardDetailDTO);
        model.addAttribute("sessionDTO", sessionDTO);

        return "board/notice-edit";
    }

    // 공지사항 수정
    @PostMapping("admin/notice-edit/{boardNum}")
    public String noticeEdit(@PathVariable Long boardNum, @Valid BoardDTO boardDTO, BindingResult bindingResult,
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

        return "redirect:/notice/{boardNum}";
    }

    // 공지사항 삭제
    @GetMapping("admin/notice-delete/{boardNum}")
    public String noticeDelete(@PathVariable Long boardNum) {

        BoardDetailDTO boardDetailDTO = boardService.findOne_Board(boardNum);
        boardService.deleteBoard(boardDetailDTO);

        return "redirect:/notice";
    }

}
