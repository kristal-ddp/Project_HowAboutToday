package com.phoenix.howabouttoday.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrdersDirectDTO {

    private String daterange;
    private Long roomNum;
    private Integer adult_number;
    private Integer child_number;

    public OrdersDirectDTO() {
    }

    public OrdersDirectDTO(String daterange, Long roomNum, Integer adult_number, Integer child_number) {
        this.daterange = daterange;
        this.roomNum = roomNum;
        this.adult_number = adult_number;
        this.child_number = child_number;
    }
}