package com.phoenix.howabouttoday.payment.enumType;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ReviewResponseCode {

    NOT_MEMBER("0"),
    NOT_RESERVE("1"),
    OVER_TWO_WEEKS("2"),
    WRITE_POSSIBLE("3");


    private String value;

    ReviewResponseCode(String value) {
        this.value = value;
    }
}


