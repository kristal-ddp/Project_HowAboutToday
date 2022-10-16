package com.phoenix.howabouttoday.room.service;

import com.phoenix.howabouttoday.room.entity.Room;
import com.phoenix.howabouttoday.room.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    //객실 리스트 처리
    public List<Room> roomList() {

        return roomRepository.findAll();

    }

    //객실 상세 페이지 처리

}
