package com.phoenix.howabouttoday.payment.enumType;

import lombok.Getter;
import java.util.Arrays;

@Getter
public enum DiscountType {

    FLAT("금액 할인"),
    FIXED("비율 할인");

    private String value;

    DiscountType(String value) {
        this.value = value;
    }

    public static DiscountType fromCode(String dbData){
        return Arrays.stream(DiscountType.values())
                .filter(v -> v.getValue().equals(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("할인방법에 %s가 존재하지 않습니다.", dbData)));
    }
}


