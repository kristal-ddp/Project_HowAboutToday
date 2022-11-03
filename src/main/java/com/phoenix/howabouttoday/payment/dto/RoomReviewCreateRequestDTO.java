package com.phoenix.howabouttoday.payment.dto;

import lombok.Getter;

@Getter
public class RoomReviewCreateRequestDTO {

    private Long roomNum;
    private Long ordersDetailNum;
    private String name;
    private String content;
    private Double rating;
}
