package com.phoenix.howabouttoday.reserve.domain;

import com.phoenix.howabouttoday.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
