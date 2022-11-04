package com.phoenix.howabouttoday.accom.entity;

import lombok.*;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
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

