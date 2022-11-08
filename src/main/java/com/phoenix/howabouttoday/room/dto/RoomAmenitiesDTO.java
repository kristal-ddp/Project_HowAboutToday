package com.phoenix.howabouttoday.room.dto;

import com.phoenix.howabouttoday.room.entity.Amenities;
import com.phoenix.howabouttoday.room.entity.AmenitiesNames;
import com.phoenix.howabouttoday.room.entity.RoomViewAmenities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RoomAmenitiesDTO {

    private Long amenitiesNum;//시설번호

    private Long roomNum; //객실 번호

    private AmenitiesNames amenitiesName; //시설 이름

    private String originFileName;

    public RoomAmenitiesDTO(RoomViewAmenities roomViewAmenities) {
        this.roomNum = roomViewAmenities.getRoom().getRoomNum();
        this.amenitiesName = roomViewAmenities.getAmenities().getAmenitiesName();
        this.amenitiesNum = roomViewAmenities.getAmenities().getAmenitiesNum();
        this.originFileName = roomViewAmenities.getAmenities().getAmenitiesOriginalFileName();

    }


}
