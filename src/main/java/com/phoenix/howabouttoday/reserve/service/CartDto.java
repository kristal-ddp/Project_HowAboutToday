package com.phoenix.howabouttoday.reserve.service;

import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class CartDto {

    @Data
    public static class RequestDto{
        private Long cartNum;
        private Long memberNum;
        private Long roomNum;
        private LocalDateTime reserveUseStartDate;
        private LocalDateTime reserveUseEndDate;

        private int reservePrice;
        private int reserveAdultCount;
        private int reserveChildCount;

    }

    @Data
    public static class ResponseDto{

        private Long cartNum;
        private Long memberNum;
        private Long roomNum;

        /** 장바구니에서 보여줄 것 **/
        private String roomName;
        private String roomFileName;

        private LocalDate reserveUseStartDate;
        private LocalDate reserveUseEndDate;

        private int reservePrice;
        private int reserveAdultCount;
        private int reserveChildCount;
        private int betweenDay;

        public ResponseDto(Cart cart) {
            this.cartNum = cart.getReserveNum();
            this.memberNum = cart.getMember().getMemberNum();
            this.roomNum = cart.getRoom().getRoomNum();
            this.roomName = cart.getRoom().getRoomName();
            this.roomFileName = cart.getRoom().getRoomImage().get(0).getRoomOriginFileName();//대표가되는 첫번째 메인이지하나만 전달
            this.reserveUseStartDate = cart.getReserveUseStartDate();
            this.reserveUseEndDate = cart.getReserveUseEndDate();
            this.reservePrice = cart.getReservePrice();
            this.reserveAdultCount = cart.getReserveAdultCount();
            this.reserveChildCount = cart.getReserveChildCount();

            //** 몇박 계산 로직 **/
            Period between = Period.between(cart.getReserveUseStartDate(),cart.getReserveUseEndDate());

            this.betweenDay = between.getDays();



        }


    }
}
