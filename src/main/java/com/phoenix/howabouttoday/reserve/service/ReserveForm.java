package com.phoenix.howabouttoday.reserve.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class ReserveForm {

    private LocalDate reserveUseStartDate;
    private LocalDate reserveUseEndDate;

    private int reserveAdultCount;
    private int reserveChildCount;


}
