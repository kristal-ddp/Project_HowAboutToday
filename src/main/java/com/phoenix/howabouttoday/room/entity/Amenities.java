package com.phoenix.howabouttoday.room.entity;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="room_roomNum")
    private Room room; //객실 번호

    @Enumerated(EnumType.STRING)
    private AmenitiesNames amenitiesName;//시설이름

    @Builder
    public Amenities(Long amenitiesNum, AmenitiesNames amenitiesName) {
        this.amenitiesNum = amenitiesNum;
        this.amenitiesName = amenitiesName;
    }


}
