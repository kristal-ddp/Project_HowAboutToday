package com.phoenix.howabouttoday.accom.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AccomViewFacilities {

    @Id @GeneratedValue
    private Long accomViewfacilitiesNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accom_num")
    private Accommodation accommodation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facilities_num")
    private Facilities facilities;

}

