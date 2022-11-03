/**
 * 결제 성공시 전달되는 정보를 받을 커맨드 객체
 * merchanId는 아임포트의 고유한 결제번호
 * cartNum은 결제된 cart의 pk 값을 저장한 리스트
 */


package com.phoenix.howabouttoday.payment.dto;

import lombok.Getter;

import java.util.List;


@Getter
public class OrdersCreateDTO {
    private String name;
    private String tel;
    private String ordersType;
    private String imp_uid;
    private String merchantId;
    private Long useCouponNum;
    private Integer discountValue;
    private List<Long> cartNum;

    public OrdersCreateDTO(String name, String tel, String ordersType, String imp_uid, String merchantId, Long useCouponNum, Integer discountValue, List<Long> cartNum) {
        this.name = name;
        this.tel = tel;
        this.ordersType = ordersType;
        this.imp_uid = imp_uid;
        this.merchantId = merchantId;
        this.useCouponNum = useCouponNum;
        this.discountValue = discountValue;
        this.cartNum = cartNum;
    }
}
