package com.phoenix.howabouttoday.reserve.domain.Reservation;


import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.room.entity.Room;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.*;


/**
 * reserveNum = PK번호
 * member = 회원
 * accommodation = 숙소
 * room = 객실
 * orders = 주문
 * reserveStatus = 예약상태 [READY,COMP]
 * reserveUseStartDate = 희망 사용 날짜
 * reserveUseEndDate = 희망 종료 날짜
 * reservePrice = 주문예상가격
 * reserveAdultCount = 인원(성인)
 * reserveChildCount = 인원(아동)
 */


@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@DiscriminatorColumn(name = "reserve_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@AllArgsConstructor
@Entity
public abstract class Reservation {

    @Id @GeneratedValue
    private Long reserveNum;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_num")
    private Member member;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "accom_num")
    private Accommodation accommodation;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_num")
    private Room room;

    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ordersNum", referencedColumnName = "ordersNum")
    protected Orders orders;

    @Enumerated(EnumType.STRING)
    private ReserveStatus reserveStatus;

    private LocalDate reserveUseStartDate;
    private LocalDate reserveUseEndDate;

    private Integer reservePrice;
    private Integer reserveAdultCount;
    private Integer reserveChildCount;
}
