package com.phoenix.howabouttoday.member.dto;

import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.entity.Role;
import lombok.Data;
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

    private Role role;

    //Entity -> DTO
    public SessionDTO(Member member) {
        this.memberNum=member.getMemberNum();
        this.email = member.getEmail();
        this.pwd = member.getPwd();
        this.nickname = member.getNickname();
        this.memberTel = member.getMemberTel();
        this.role = member.getRole();

    }
}