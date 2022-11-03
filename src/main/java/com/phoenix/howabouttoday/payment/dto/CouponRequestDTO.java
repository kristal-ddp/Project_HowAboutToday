package com.phoenix.howabouttoday.payment.dto;


import lombok.Getter;

@Getter
public class CouponRequestDTO {

    private Long couponNum;
    private Integer originPrice;

    public Boolean cancelCoupon(){
        return couponNum == 0;
    }

}
