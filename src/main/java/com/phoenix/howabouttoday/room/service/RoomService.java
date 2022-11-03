package com.phoenix.howabouttoday.room.service;

import com.phoenix.howabouttoday.room.dto.*;

import java.util.List;

public interface RoomService {
    List<RoomListDTO> findAll_Room(Long accomNum); //객실 리스트

    List<RoomAmenitiesDTO> findAll_Amenities(Long roomNum); //객실 디테일 - 시설 리스트

    List<RoomServiceDTO> findAll_Service(Long roomNum); //객실 디테일 - 서비스 리스트

    List<RoomImageDTO> findAll_Image(Long roomNum); //객실 이미지 리스트

    RoomDetailDTO findOne_Room(Long roomNum); // 객실 디테일

    List<RoomReviewDTO> findAll_Review(Long roomNum); // 객실 리뷰
}