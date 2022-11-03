package com.phoenix.howabouttoday.payment.entity;


import com.phoenix.howabouttoday.payment.enumType.CouponStatus;
import com.phoenix.howabouttoday.payment.enumType.CouponStatusConverter;
import com.phoenix.howabouttoday.payment.enumType.DiscountType;
import com.phoenix.howabouttoday.payment.enumType.DiscountTypeConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class CouponRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponRulesNum;

    @Column
    private String couponName;

    @Column
    private String couponContent;

    @Column
    private Integer period; //사용기한

    //이건 아마 enum으로 관리 해야할듯
    @Column
    @Convert(converter = DiscountTypeConverter.class)
    private DiscountType discountType;

    @Column
    private Integer discountValue;  //할인값 (금액, 비율) 에 따라서 달라진다.

    //최소주문금액
    private Integer discountMinPrice;

    //최대할인금액
    private Integer discountMaxPrice;

    public Boolean isFixed(){
        return discountType == DiscountType.FIXED;
    }
}