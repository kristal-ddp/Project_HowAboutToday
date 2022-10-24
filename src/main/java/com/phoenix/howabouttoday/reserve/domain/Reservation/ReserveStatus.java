package com.phoenix.howabouttoday.reserve.domain.Reservation;

import lombok.Getter;

/**
 * READY 이용전
 * COMP 이용후
 */

@Getter
public enum ReserveStatus {
    READY("이용 전"),
    IN_USE("이용 중"),
    COMPLETE("이용 완료"),
    CANCEL("취소");

    private String value;

    ReserveStatus(String value) {
        this.value = value;
    }
}
