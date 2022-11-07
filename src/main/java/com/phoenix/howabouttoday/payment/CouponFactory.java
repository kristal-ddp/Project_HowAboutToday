package com.phoenix.howabouttoday.payment;


import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.payment.entity.Coupon;
import com.phoenix.howabouttoday.payment.entity.CouponRules;
import com.phoenix.howabouttoday.payment.enumType.CouponNumber;
import com.phoenix.howabouttoday.payment.repository.CouponRulesRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponFactory {

    private final CouponRulesRepository couponRulesRepository;

    public void create(CouponNumber couponNumber, Member member){
        Long counponNum = couponNumber.getCONSTANT().longValue();
        CouponRules currentCouponRules = couponRulesRepository.findById(counponNum).orElseThrow(() -> new IllegalArgumentException(String.format("%d번 쿠폰 정보를 찾을 수 없습니다.", counponNum)));
        Coupon coupon = currentCouponRules.makeCoupon(member);
        member.addCoupon(coupon);
    }
}
