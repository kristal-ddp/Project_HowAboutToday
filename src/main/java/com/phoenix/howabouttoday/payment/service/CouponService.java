package com.phoenix.howabouttoday.payment.service;


import com.phoenix.howabouttoday.payment.dto.CouponDTO;
import com.phoenix.howabouttoday.payment.dto.CouponRequestDTO;
import com.phoenix.howabouttoday.payment.dto.CouponResponseDTO;
import com.phoenix.howabouttoday.payment.entity.Coupon;
import com.phoenix.howabouttoday.payment.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CouponService {

    private final CouponRepository couponRepository;


    public List<CouponDTO> getCoupon(Long memberNum){

        List<CouponDTO> couponDTOList = couponRepository.findAllByMember_MemberNum(memberNum).stream()
                .map(CouponDTO::new)
                .collect(Collectors.toList());
        return couponDTOList;
    }

    /**
     * 쿠폰 사용시 체크할 것
     * 1. 쿠폰을 사용하려면 totalPrice가 최소결제금액 이상이어야한다.
     * 2. 쿠폰 적용 후 0보다 크게 만들어야 한다.
     * 3. 쿠폰의 타입별로 계산 방식이 다르다.
     * 4. 반환값으로 사용을 할 수 있는지 없는 지 알려줘야한다.
     *
     * **/

    public CouponResponseDTO calculateCoupon(Long memberNum, CouponRequestDTO couponRequestDTO){
        if (couponRequestDTO.cancelCoupon()){
            return new CouponResponseDTO(true, couponRequestDTO.getOriginPrice(), 0, 0L, "쿠폰 적용이 취소되었습니다.");
        }

//        Coupon coupon = couponRepository.findById(couponRequestDTO.getCouponNum()).orElseThrow(() -> new IllegalArgumentException(String.format("해당하는 %d번 쿠폰 정보가 없습니다.", couponRequestDTO.getCouponNum())));
        Coupon coupon = couponRepository.findByCouponNumAndMember_MemberNum(couponRequestDTO.getCouponNum(), memberNum).orElseThrow(() -> new IllegalArgumentException(String.format("해당하는 %d번 쿠폰 정보가 없습니다.", couponRequestDTO.getCouponNum())));
        if (!coupon.isPossible(couponRequestDTO.getOriginPrice())){
            return new CouponResponseDTO(false, -1, -1, 0L, "최소결제금액 이상이어야 쿠폰 사용이 가능합니다.");
        }

        Double discountPrice = 0d;  //할인 가격
        Double resultPrice = 0d;    //할인이 적용된 최종 가격

        if (coupon.getCouponRules().isFixed()){
            discountPrice = couponRequestDTO.getOriginPrice() * coupon.getCouponRules().getDiscountValue() / 100.0;
            if (discountPrice > coupon.getCouponRules().getDiscountMaxPrice()){
                discountPrice = coupon.getCouponRules().getDiscountMaxPrice().doubleValue();
            }
            resultPrice = couponRequestDTO.getOriginPrice() - discountPrice;
        }
        else{
            discountPrice = coupon.getCouponRules().getDiscountValue().doubleValue();
            resultPrice = couponRequestDTO.getOriginPrice() - discountPrice;
            if (resultPrice < 0){
                resultPrice = 0d;
            }
        }

        return new CouponResponseDTO(true, resultPrice.intValue(), discountPrice.intValue(), coupon.getCouponNum(), "");
    }

}
