package com.phoenix.howabouttoday.accom.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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

