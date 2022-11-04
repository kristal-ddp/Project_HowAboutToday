package com.phoenix.howabouttoday.accom.dto;

import lombok.Data;

import javax.annotation.PostConstruct;
import java.time.LocalDate;


@Data
public class SearchForm {
    /** 현재날짜 / 다음날 날짜를 기본값으로 설정해줌
     * 성인 2명이 기본값
     * 아동은 0명이 기본값
     * **/
    private String daterange = LocalDate.now().toString().replaceAll("-","/") + " - " +
            LocalDate.now().plusDays(1).toString().replaceAll("-","/");
    private int adult_number = 2;
    private int child_number = 0;



}
