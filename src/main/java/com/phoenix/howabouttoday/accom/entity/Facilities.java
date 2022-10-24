package com.phoenix.howabouttoday.accom.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
    숙소 별로 시설 종류를 고민해야할 것.
    숙소 별로 특정 시설로 구분지어 차별성을 줄것.(호텔로 예로 들면 룸서비스,픽업 등등?)

    */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Facilities {

    @Id
    @GeneratedValue
    @Column
    private Long facilitiesNum;//시설번호

    @Enumerated(EnumType.STRING)
    private Facility facility;//시설이름

    private String faciltiesOriginalFileName;
    private String faciltiesSaveFilename;

    @Builder
    public Facilities(String faciltiesOriginalFileName, String faciltiesSaveFilename, Facility facility) {
        this.faciltiesOriginalFileName = faciltiesOriginalFileName;
        this.faciltiesSaveFilename = faciltiesSaveFilename;
        this.facility = facility;
    }
}

