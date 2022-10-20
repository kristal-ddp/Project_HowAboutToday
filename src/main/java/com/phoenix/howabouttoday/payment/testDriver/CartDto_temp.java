package com.phoenix.howabouttoday.payment.testDriver;

import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.room.entity.Room;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CartDto_temp {


    private Long reserveNum;

    private Member member;

    private Accommodation accommodation;

    private Room room;

    private LocalDate reserveUseStartDate;

    private LocalDate reserveUseEndDate;

    private int reservePrice;

    private int reserveAdultCount;

}
