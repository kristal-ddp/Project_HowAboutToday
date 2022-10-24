package com.phoenix.howabouttoday.room.repository;

import com.phoenix.howabouttoday.room.entity.RoomViewAmenities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomViewAmenitiesRepository extends JpaRepository<RoomViewAmenities,Long> {

    //Amenities List
    List<RoomViewAmenities> findAllByRoom_RoomNum(Long roomNum);

}

