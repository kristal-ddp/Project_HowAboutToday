package com.phoenix.howabouttoday.member.dto;

import com.phoenix.howabouttoday.member.entity.Role;
import com.phoenix.howabouttoday.member.entity.Member;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MemberDTO {

    private Long num;

    private String email;

    private String pwd;

    private LocalDate joinDate;

    private String nickname;

    private String memberTel;

    private Role role;

    //DTO -> Entity
    public Member toEntity() {
        Member member = Member.builder()
                .email(email)
                .pwd(pwd)
                .nickname(nickname)
                .joinDate(LocalDate.now())
                .memberTel(memberTel)
                .role(role.MEMBER)
                .build();
        return member;
    }
}