package com.phoenix.howabouttoday.member.dto;


import com.phoenix.howabouttoday.member.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberCreateDTO {

    private Long memberNum;
    private Role memberCode;
}
