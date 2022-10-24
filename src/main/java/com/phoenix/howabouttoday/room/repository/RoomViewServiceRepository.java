package com.phoenix.howabouttoday.room.repository;

import com.phoenix.howabouttoday.room.entity.RoomViewService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomViewServiceRepository extends JpaRepository<RoomViewService,Long> {

    //Service List
    List<RoomViewService> findAllByRoom_RoomNum(Long roomNum);

}
