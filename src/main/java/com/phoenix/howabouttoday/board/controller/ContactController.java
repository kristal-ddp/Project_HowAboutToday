package com.phoenix.howabouttoday.board.controller;

import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ContactController {

    // 고객센터
    @GetMapping("contact")
    public String contact(@LoginUser SessionDTO sessionDTO, Model model, MemberDTO memberDTO){

        if(sessionDTO != null) {
            model.addAttribute("sessionDTO", sessionDTO);
        }

        return "board/contact";
    }

}
