package com.phoenix.howabouttoday.member.dto;

import com.phoenix.howabouttoday.member.entity.Code;
import com.phoenix.howabouttoday.member.entity.Member;
import lombok.Getter;

import java.io.Serializable;

//세션
@Getter
public class SessionDTO implements Serializable { //세션 저장 클래스

    private Long memberNum;

    private String email;

    private String pwd;

    private String nickname;

    private String memberTel;

    private Code memberCode;

    //Entity -> DTO
    public SessionDTO(Member member) {
        this.memberNum=member.getMemberNum();
        this.email = member.getEmail();
        this.pwd = member.getPwd();
        this.nickname = member.getNickname();
        this.memberTel = member.getMemberTel();
        this.memberCode = member.getMemberCode();

    }
}