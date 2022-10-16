package com.phoenix.howabouttoday.payment.testDriver;

import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.payment.Orders;
import com.phoenix.howabouttoday.reserve.domain.Reservation.ReserveStatus;
import com.phoenix.howabouttoday.room.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;

@Getter
@AllArgsConstructor
@Builder
public class CartDto_1 {


    private Long reserveNum;

    private Member member;

    private Accommodation accommodation;

    private Room room;

    private LocalDate reserveUseStartDate;

    private LocalDate reserveUseEndDate;

    private int reservePrice;

    private int reserveAdultCount;

}
