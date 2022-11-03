package com.phoenix.howabouttoday.global;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum OrdersStatus {
    
    PAYMENT_COMPLETE("결제완료"),
    PAYMENT_CANCEL("취소완료"),
    DONE("이용완료");

    private String value;

    OrdersStatus(String value) {
        this.value = value;
    }

    public static OrdersStatus fromCode(String dbData){
        return Arrays.stream(OrdersStatus.values())
                .filter(v -> v.getValue().equals(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("상태 %s가 존재하지 않습니다.", dbData)));
    }
}


