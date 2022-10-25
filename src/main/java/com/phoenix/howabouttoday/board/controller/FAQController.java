package com.phoenix.howabouttoday.board.controller;

import com.phoenix.howabouttoday.board.dto.*;
import com.phoenix.howabouttoday.board.service.FAQService;
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
public class FAQController {

    private final FAQService faqService;

    // FAQ 리스트 페이지
    @GetMapping("faq")
    public String faqList(@LoginUser SessionDTO sessionDTO, Model model){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        List<List<BoardDetailDTO>> faqList = faqService.findAll_FAQ("FAQ"); // boardCategoryName = "FAQ"인 데이터들을 DTO에 저장
        model.addAttribute("lists", faqList);

        return "board/faq";
    }

    // FAQ 작성 페이지
    @GetMapping("admin/faq-add")
    public String faqAdd(@ModelAttribute("faqAddDTO") FAQAddDTO faqAddDTO,
                         @LoginUser SessionDTO sessionDTO, Model model){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        faqAddDTO.setMemberNum(sessionDTO.getMemberNum());

        return "board/faq-add";
    }

    // FAQ 작성
    @PostMapping("admin/faq-add")
    public String faqAdd(@Valid FAQAddDTO faqAddDTO, BindingResult bindingResult,
                         @LoginUser SessionDTO sessionDTO, Model model){

        if(bindingResult.hasErrors()) {
            model.addAttribute("sessionDTO", sessionDTO);
            return "board/faq-add";
        }

        faqService.addFAQ(faqAddDTO);

        return "redirect:/faq";
    }

}
