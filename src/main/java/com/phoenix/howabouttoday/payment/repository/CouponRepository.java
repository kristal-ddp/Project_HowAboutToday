package com.phoenix.howabouttoday.payment.repository;

import com.phoenix.howabouttoday.payment.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    List<Coupon> findAllByMember_MemberNum(Long memberNum);

    Optional<Coupon> findByCouponNumAndMember_MemberNum(Long couponNum, Long memberNum);

}