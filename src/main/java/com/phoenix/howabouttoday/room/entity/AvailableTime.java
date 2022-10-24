package com.phoenix.howabouttoday.room.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AvailableTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long availableTimeId;

//    @Column
//    private Integer time;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "availableDataId")
//    private AvailableDate availableDate;
//
//
//    @Builder
//    public AvailableTime(Integer time, AvailableDate availableDate) {
//        this.time = time;
//        this.availableDate = availableDate;
//    }
}