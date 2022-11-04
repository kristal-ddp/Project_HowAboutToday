package com.phoenix.howabouttoday.member.dto;

import com.phoenix.howabouttoday.member.entity.Role;
import com.phoenix.howabouttoday.member.entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MemberDTO {

    private Long num;

    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일 필수 입력")
    private String email;



    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{6,12}",
            message = "영문자와 숫자, 특수문자가 1개 이상 포함된 6자~12자의 비밀번호여야 합니다.")
    @NotBlank(message = "비밀번호 필수 입력")
    private String pwd;

    private LocalDate joinDate;


    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "이름은 특수문자를 제외한 2~10자리여야 합니다.")
    @NotBlank(message = "이름 필수 입력")
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