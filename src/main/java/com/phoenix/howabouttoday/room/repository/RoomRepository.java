package com.phoenix.howabouttoday.room.repository;

import com.phoenix.howabouttoday.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

}