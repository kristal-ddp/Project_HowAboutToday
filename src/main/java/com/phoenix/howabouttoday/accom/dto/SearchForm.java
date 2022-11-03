package com.phoenix.howabouttoday.accom.dto;

import lombok.Data;

@Data
public class SearchForm {

    private String daterange;
    private int adult_number = 2;
    private int child_number = 0;

}
