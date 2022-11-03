package com.phoenix.howabouttoday.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrdersDirectDTO {

    private String daterange;
    private Long roomNum;
    private Integer adult_qty;
    private Integer child_qty;

    public OrdersDirectDTO() {
    }

    public OrdersDirectDTO(String daterange, Long roomNum, Integer adult_qty, Integer child_qty) {
        this.daterange = daterange;
        this.roomNum = roomNum;
        this.adult_qty = adult_qty;
        this.child_qty = child_qty;
    }
}