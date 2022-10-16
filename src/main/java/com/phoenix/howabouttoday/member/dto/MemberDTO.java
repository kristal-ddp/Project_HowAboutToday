package com.phoenix.howabouttoday.member.dto;

import com.phoenix.howabouttoday.member.entity.Code;
import com.phoenix.howabouttoday.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MemberDTO {

    private String email;

    private String pwd;

    private String nickname;

    private String memberTel;

    private Code memberCode;

    //DTO -> Entity
    public Member toEntity() {
        Member member = Member.builder()
                .email(email)
                .pwd(pwd)
                .nickname(nickname)
                .memberTel(memberTel)
                .memberCode(memberCode.MEMBER)
                .build();
            return member;
        }
    }


