package com.phoenix.howabouttoday.reserve.controller;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CartForm {

    private String checkDate;
    private int adultQty;
    private int childQty;


}
