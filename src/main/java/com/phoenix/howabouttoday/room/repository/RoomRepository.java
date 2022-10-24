package com.phoenix.howabouttoday.room.repository;

import com.phoenix.howabouttoday.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    // Room Detail
    Optional<Room> findByRoomNum(Long roomNum);

    //Room List
    List<Room> findAllByAccommodationAccomNum(Long accomNum);

}