package com.phoenix.howabouttoday.payment.dto;


import lombok.Getter;

@Getter
public class CouponResponseDTO {

    private Boolean isPossible;     //할인이 가능한지 여부
    private Integer resultPrice;  //쿠폰이 적용된 최종 가격
    private Integer discountPrice;  //쿠폰으로 할인된 가격
    private Long useCouponNum;
    private String failReason;


    public CouponResponseDTO(Boolean isPossible, Integer resultPrice, Integer discountPrice, Long useCouponNum, String failReason) {
        this.isPossible = isPossible;
        this.resultPrice = resultPrice;
        this.discountPrice = discountPrice;
        this.useCouponNum = useCouponNum;
        this.failReason = failReason;
    }
}
