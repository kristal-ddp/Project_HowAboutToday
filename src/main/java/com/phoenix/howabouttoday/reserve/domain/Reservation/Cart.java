package com.phoenix.howabouttoday.reserve.domain.Reservation;

import com.phoenix.howabouttoday.payment.dto.OrdersDetailDTO;

import lombok.*;
import lombok.experimental.SuperBuilder;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.TextStyle;
import java.util.Locale;


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

    public OrdersDetailDTO transDto(){

        Period period = Period.between(this.getReserveUseStartDate(), this.getReserveUseEndDate());
        String checkIn = this.getRoom().getAccommodation().getCheckIn().toString();
        String checkOut = this.getRoom().getAccommodation().getCheckOut().toString();

        // 2. DayOfWeek 객체 구하기
        DayOfWeek startday = this.getReserveUseStartDate().getDayOfWeek();
        DayOfWeek endday = this.getReserveUseEndDate().getDayOfWeek();

        String startWeek = startday.getDisplayName(TextStyle.NARROW, Locale.KOREAN);  // 토
        String endWeek = endday.getDisplayName(TextStyle.SHORT, Locale.KOREAN);  // 토

        NumberFormat numberFormat = NumberFormat.getInstance();
        String price = numberFormat.format(this.getReservePrice());

        return OrdersDetailDTO.builder()
                .accomType(this.getRoom().getAccommodation().getAccomCategory().getValue())
                .accomName(this.getRoom().getAccommodation().getAccomName())
                .accomRegion(this.getRoom().getAccommodation().getRegion().getRegion().getValue())
                .orderDate(LocalDate.now().toString())
                .usePeriod(String.valueOf(period.getDays()))
                .startDate(this.getReserveUseStartDate().toString())
                .endDate(this.getReserveUseEndDate().toString())
                .startWeek(startWeek)
                .endWeek(endWeek)
                .price(price)
                .usedStatus(this.getReserveStatus().toString())
                .roomName(this.getRoom().getRoomName())
                .checkIn(checkIn)
                .checkOut(checkOut)
                .build();
    }
}
