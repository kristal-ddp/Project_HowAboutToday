
/**
 * 멤버가 가지고 있는 1번의 주문에 대한 주문정보 테이블
 *
 */

package com.phoenix.howabouttoday.payment.entity;

import com.phoenix.howabouttoday.global.OrdersStatus;
import com.phoenix.howabouttoday.global.OrdersStatusConverter;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Reservation;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNum")
    private Member member;

    @Column(length = 50)
    private String ordersTel;

    @Column(length = 50)
    private String ordersName;

    @Column(length = 50)
    private LocalDateTime ordersDate;

    private Integer ordersPrice;

    @Column(length = 50)
    private String ordersType;

    @Column(length = 50)
    @Convert(converter = OrdersStatusConverter.class)
    private OrdersStatus ordersStatus;

    @Column(length = 500, unique = true)
    private String impUid;

    @Column(length = 500, unique = true)
    private String merchantId;

    @Column
    private Integer discountedPrice;

    @Column
    private Long couponNum;

    @Column
    private Integer discountValue;

    @NotNull
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Reservation> reservation = new ArrayList<>(); //이미지 fk를 위한 매핑

    @Builder
    public Orders(Member member, String ordersTel, String ordersName, LocalDateTime ordersDate, Integer ordersPrice, String ordersType, OrdersStatus ordersStatus, String impUid, String merchantId, Long couponNum, Integer discountValue) {
        this.member = member;
        this.ordersTel = ordersTel;
        this.ordersName = ordersName;
        this.ordersDate = ordersDate;
        this.ordersPrice = ordersPrice;
        this.ordersType = getType(ordersType);
        this.ordersStatus = ordersStatus;
        this.impUid = impUid;
        this.merchantId = merchantId;
        this.discountedPrice = ordersPrice - discountValue;
        this.couponNum = couponNum;
        this.discountValue = discountValue;
    }

    private String getType(String ordersType){

        if(ordersType.equals("vbank")){
            return "가상계좌";
        }
        else if(ordersType.equals("card")){
            return "카드";
        }
        else if(ordersType.equals("phone")){
            return "핸드폰결제";
        }

        return "알 수 없음";
    }

    public void changeStatusToCancel(){
        this.ordersStatus = OrdersStatus.PAYMENT_CANCEL;
    }

    //스케쥴러로 상태 변경 시 사용할 메서드
    public void changeStatusToInUse(){
        this.ordersStatus = OrdersStatus.IN_USE;
    }

    //스케쥴러로 상태 변경 시 사용할 메서드
    public void changeStatusToComplete(){
        this.ordersStatus = OrdersStatus.DONE;
    }

}