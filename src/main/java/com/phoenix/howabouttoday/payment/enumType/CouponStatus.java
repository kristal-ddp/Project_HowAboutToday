package com.phoenix.howabouttoday.payment.enumType;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CouponStatus {

//    ASSIGNED("지급 전"),
    AVAILABLE("사용 전"),
    DONE("사용 후");

    private String value;

    CouponStatus(String value) {
        this.value = value;
    }

    public static CouponStatus fromCode(String dbData){
        return Arrays.stream(CouponStatus.values())
                .filter(v -> v.getValue().equals(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("쿠폰상태 %s가 존재하지 않습니다.", dbData)));
    }
}


