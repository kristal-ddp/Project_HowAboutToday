package com.phoenix.howabouttoday.payment.enumType;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ReviewStatus {

    PRE_WRITE("작성 전"),
    POST_WRITE("작성 완료");

    private String value;

    ReviewStatus(String value) {
        this.value = value;
    }

    public static ReviewStatus fromCode(String dbData){
        return Arrays.stream(ReviewStatus.values())
                .filter(v -> v.getValue().equals(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("쿠폰상태 %s가 존재하지 않습니다.", dbData)));
    }
}


