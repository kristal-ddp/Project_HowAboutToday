package com.phoenix.howabouttoday.member.dto;


import com.phoenix.howabouttoday.member.entity.Code;
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
    private Code memberCode;
}
