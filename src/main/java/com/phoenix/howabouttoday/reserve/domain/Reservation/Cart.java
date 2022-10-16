package com.phoenix.howabouttoday.reserve.domain.Reservation;

import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.payment.Orders;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Reservation;
import com.phoenix.howabouttoday.room.entity.Room;
import lombok.*;
import lombok.experimental.SuperBuilder;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


@DiscriminatorValue("cart")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
public class Cart extends Reservation {

    // @Builder
    // public Cart(Long reserveNum, Member member, Accommodation accommodation, Room room, Orders orders, ReserveStatus reserveStatus, LocalDate reserveUseStartDate, LocalDate reserveUseEndDate, int reservePrice, int reserveAdultCount, int reserveChildCount) {
    //     super(reserveNum, member, accommodation, room, orders, reserveStatus, reserveUseStartDate, reserveUseEndDate, reservePrice, reserveAdultCount, reserveChildCount);
    // }
}
