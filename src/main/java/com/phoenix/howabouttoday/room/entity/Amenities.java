package com.phoenix.howabouttoday.room.entity;

import com.phoenix.howabouttoday.global.AmenTypeConverter;
import com.phoenix.howabouttoday.global.FacTypeConverter;
import lombok.*;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Amenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long amenitiesNum;//시설번호


    @Convert(converter = AmenTypeConverter.class)
    private AmenitiesNames amenitiesName;//시설이름

    private String amenitiesOriginalFileName;

    @Builder
    public Amenities(Long amenitiesNum, AmenitiesNames amenitiesName) {
        this.amenitiesNum = amenitiesNum;
        this.amenitiesName = amenitiesName;
    }


}
