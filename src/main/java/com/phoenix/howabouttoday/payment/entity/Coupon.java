package com.phoenix.howabouttoday.payment.entity;


import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.payment.enumType.CouponStatus;
import com.phoenix.howabouttoday.payment.enumType.CouponStatusConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couponRulesNum")
    private CouponRules couponRules;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNum")
    private Member member;

    //이것도 enum으로?
    @Column
    @Convert(converter = CouponStatusConverter.class)
    private CouponStatus status;

    @Column
    private LocalDate startDate; //지급날짜

    @Column
    private LocalDate endDate;      //만료날짜

    public Boolean isPossible(Integer originPrice){
        return originPrice >= getCouponRules().getDiscountMinPrice();
    }

    public void couponUsed(){
        status = CouponStatus.DONE;
    }
}