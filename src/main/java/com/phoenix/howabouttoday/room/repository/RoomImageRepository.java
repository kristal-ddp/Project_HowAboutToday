package com.phoenix.howabouttoday.room.repository;

import com.phoenix.howabouttoday.room.entity.RoomImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomImageRepository extends JpaRepository<RoomImage, Long> {

    //Image List
    List<RoomImage> findAllByRoom_RoomNum(Long roomNum);

}
