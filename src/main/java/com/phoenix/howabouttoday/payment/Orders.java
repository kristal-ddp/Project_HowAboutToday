
package com.phoenix.howabouttoday.payment;

import com.phoenix.howabouttoday.reserve.domain.Reservation.Reservation;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersNum;


    @Column(length = 50)
    private String ordersTel;


    @Column(length = 50)
    private String ordersName;


    @Column(length = 50)
    private String ordersDate;


    private Integer ordersPrice;


    @Column(length = 50)
    private String ordersType;


    @Column(length = 50)
    private String ordersStatus;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Reservation> reservation = new ArrayList<>(); //이미지 fk를 위한 매핑

    @Builder
    public Orders(Long ordersNum, String ordersTel, String ordersName, String ordersDate, Integer ordersPrice, String ordersType, String ordersStatus) {
        this.ordersNum = ordersNum;
        this.ordersTel = ordersTel;
        this.ordersName = ordersName;
        this.ordersDate = ordersDate;
        this.ordersPrice = ordersPrice;
        this.ordersType = ordersType;
        this.ordersStatus = ordersStatus;
    }
}