package com.phoenix.howabouttoday.payment;

import com.phoenix.howabouttoday.member.dto.MemberDTO;
import org.springframework.ui.Model;

public class MemberDTOCHECK {

    public static void doCheck(Model model){
        /** 회원 Object 반환하는 로직 **/
        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("memberDTO",memberDTO);
        boolean memberCheck = false;
        model.addAttribute("memberCheck",memberCheck);
    }
}

