package com.phoenix.howabouttoday.payment.dto;

import com.phoenix.howabouttoday.payment.entity.Coupon;
import com.phoenix.howabouttoday.payment.enumType.CouponStatus;
import com.phoenix.howabouttoday.payment.enumType.DiscountType;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CouponDTO {

    private Long couponNum;         //실제 쿠폰 db의 pk
    
    private String status;    //사용여부 상태

    private String startDate; //지급날짜

    private String endDate;      //만료날짜
    
    private String couponName;      //쿠폰 이름

    private String couponContent;   //쿠폰 내용

    private String discountType;  //고정할인인지 비율할인인지

    private Integer discountValue;  //할인값 (금액, 비율) 에 따라서 달라진다.
    
    private Integer discountMinPrice;   //최소결제금액(쿠폰을 사용할 수 있는 최소 결제금액)

    private Integer discountMaxPrice;   //비율로 결제할때 최대로 할인 되는 금액

//    private CouponRules couponRules;
//    private Member member;
//    private Long couponRulesNum;
//    private Integer period; //사용기한


    public CouponDTO(Coupon coupon) {
        this.couponNum = coupon.getCouponNum();
        this.status = coupon.getStatus().getValue();
        this.startDate = coupon.getStartDate().toString();
        this.endDate = coupon.getEndDate().toString();
        this.couponName = coupon.getCouponRules().getCouponName();
        this.couponContent = coupon.getCouponRules().getCouponContent();
        this.discountType = coupon.getCouponRules().getDiscountType().getValue();
        this.discountValue = coupon.getCouponRules().getDiscountValue();
        this.discountMinPrice = coupon.getCouponRules().getDiscountMinPrice();
        this.discountMaxPrice = coupon.getCouponRules().getDiscountMaxPrice();
    }

    public Boolean isFixed(){
        return getDiscountType().equals(DiscountType.FIXED.getValue());
    }
    public Boolean isDone(){
        return status.equals(CouponStatus.DONE.getValue());
    }

}
