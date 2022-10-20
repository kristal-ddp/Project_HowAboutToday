
/**
 * 주문내역에서 보여줄 주문 dto
 *
 */

package com.phoenix.howabouttoday.payment.dto;

import com.phoenix.howabouttoday.reserve.domain.Reservation.Reservation;
import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Builder
public class OrdersDetailDTO {
    private String accomType;
    private String accomName;
    private String accomRegion;
    private String orderDate;
    private String usePeriod;
    private String startDate;
    private String endDate;
    private String startWeek;
    private String endWeek;
    private String price;
    private String usedStatus;
    private String roomName;
    private String checkIn;
    private String checkOut;


    public OrdersDetailDTO(String accomType, String accomName, String accomRegion, String orderDate, String usePeriod, String startDate, String endDate, String startWeek, String endWeek, String price, String usedStatus, String roomName, String checkIn, String checkOut) {
        this.accomType = accomType;
        this.accomName = accomName;
        this.accomRegion = accomRegion;
        this.orderDate = orderDate;
        this.usePeriod = usePeriod;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.price = price;
        this.usedStatus = usedStatus;
        this.roomName = roomName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public OrdersDetailDTO(Reservation reservation) {

        Period period = Period.between(reservation.getReserveUseStartDate(), reservation.getReserveUseEndDate());
        DayOfWeek startday = reservation.getReserveUseStartDate().getDayOfWeek();
        DayOfWeek endday = reservation.getReserveUseEndDate().getDayOfWeek();

        this.accomType = reservation.getRoom().getAccommodation().getAccomCategory().getValue();
        this.accomName = reservation.getRoom().getAccommodation().getAccomName();
        this.accomRegion = reservation.getRoom().getAccommodation().getRegion().getRegion().getValue();
        this.orderDate = LocalDate.now().toString();
        this.usePeriod = String.valueOf(period.getDays());
        this.startDate = reservation.getReserveUseStartDate().toString();
        this.endDate = reservation.getReserveUseEndDate().toString();
        this.startWeek = startday.toString();
        this.endWeek = endday.toString();
        this.price = String.valueOf(reservation.getReservePrice());
        this.usedStatus = reservation.getReserveStatus().toString();
        this.roomName = reservation.getRoom().getRoomName();
        this.checkIn = reservation.getRoom().getAccommodation().getCheckIn().toString();
        this.checkOut = reservation.getRoom().getAccommodation().getCheckOut().toString();
    }
}
