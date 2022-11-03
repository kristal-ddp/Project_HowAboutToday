package com.phoenix.howabouttoday.board.controller;

import com.phoenix.howabouttoday.board.dto.*;
import com.phoenix.howabouttoday.board.service.FAQService;
import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
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
public class FAQController {

    private final FAQService faqService;

    // FAQ 리스트 페이지
    @GetMapping("faq")
    public String faqList(@LoginUser SessionDTO sessionDTO, Model model,
                          @RequestParam(defaultValue = "") String keyword, MemberDTO memberDTO){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        List<List<BoardDetailDTO>> faqList = faqService.findAll_FAQ("FAQ", keyword); // boardCategoryName = "FAQ"인 데이터들을 DTO에 저장

        model.addAttribute("lists", faqList);
        model.addAttribute("keyword", keyword);

        return "board/faq";
    }

    // FAQ 작성 페이지
    @GetMapping("admin/faq-add")
    public String faqAdd(@ModelAttribute("FAQDTO") FAQDTO FAQDTO,
                         @LoginUser SessionDTO sessionDTO, Model model, MemberDTO memberDTO){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        FAQDTO.setMemberNum(sessionDTO.getMemberNum());

        return "board/faq-add";
    }

    // FAQ 작성
    @PostMapping("admin/faq-add")
    public String faqAdd(@Valid FAQDTO FAQDTO, BindingResult bindingResult,
                         @LoginUser SessionDTO sessionDTO, Model model, MemberDTO memberDTO){

        if(bindingResult.hasErrors()) {
            model.addAttribute("sessionDTO", sessionDTO);
            return "board/faq-add";
        }

        faqService.addFAQ(FAQDTO);

        return "redirect:/faq";
    }

    // FAQ 수정 페이지
    @GetMapping("admin/faq-edit/{boardNum}")
    public String faqEdit(@PathVariable Long boardNum, Model model,
                          @LoginUser SessionDTO sessionDTO, MemberDTO memberDTO){

        if(sessionDTO == null) {
            return "/loginProc";
        }

        BoardDetailDTO boardDetailDTO = faqService.findOne_FAQ(boardNum);
        model.addAttribute("boardDetailDTO", boardDetailDTO);
        model.addAttribute("sessionDTO", sessionDTO);

        return "board/faq-edit";
    }

    // FAQ 수정
    @PostMapping("admin/faq-edit/{boardNum}")
    public String faqEdit(@PathVariable Long boardNum, @Valid FAQDTO FAQDTO, BindingResult bindingResult,
                          @LoginUser SessionDTO sessionDTO, Model model, MemberDTO memberDTO){

        if(bindingResult.hasErrors()) {

            if(sessionDTO == null) {
                return "/loginProc";
            }

            BoardDetailDTO boardDetailDTO = faqService.findOne_FAQ(boardNum);
            model.addAttribute("boardDetailDTO", boardDetailDTO);
            model.addAttribute("sessionDTO", sessionDTO);

            return "board/faq-edit";
        }

        faqService.editFAQ(boardNum, FAQDTO);

        return "redirect:/faq";
    }

    // FAQ 삭제
    @GetMapping("admin/faq-delete/{boardNum}")
    public String faqDelete(@PathVariable Long boardNum) {

        BoardDetailDTO boardDetailDTO = faqService.findOne_FAQ(boardNum);
        faqService.deleteFAQ(boardDetailDTO);

        return "redirect:/faq";
    }

}
