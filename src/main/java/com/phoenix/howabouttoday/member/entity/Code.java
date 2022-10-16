package com.phoenix.howabouttoday.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Code {

    MEMBER("CODE_MEMBER"),
    ADMIN("CODE_ADMIN");

    private final String value;
}
