package com.phoenix.howabouttoday.payment.enumType;

import lombok.Getter;

@Getter
public enum CouponNumber {

    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    SIXTH(6),
    SEVENTH(7),
    EIGHTH(8),
    NINTH(9),
    TENTH(10),
    ELEVENTH(11),
    TWELFTH(12),
    ;

    private Integer CONSTANT;

    CouponNumber(Integer number) {
        this.CONSTANT = number;
    }
}
