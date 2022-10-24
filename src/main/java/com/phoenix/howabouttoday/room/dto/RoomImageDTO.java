package com.phoenix.howabouttoday.room.dto;

import com.phoenix.howabouttoday.room.entity.RoomImage;
import lombok.Getter;

@Getter
public class RoomImageDTO {

    private Long roomImageNum; //객실 이미지 번호
    private Long roomNum; //객실 번호
    private String roomOriginFileName; //기존 파일 이름
    private String roomSaveFileName; //저장 파일 이름

    public RoomImageDTO(RoomImage roomImage) {
        this.roomImageNum = roomImage.getRoomImageNum();
        this.roomNum = roomImage.getRoom().getRoomNum();
        this.roomOriginFileName = roomImage.getRoomOriginFileName();
        this.roomSaveFileName = roomImage.getRoomSaveFileName();
    }
}