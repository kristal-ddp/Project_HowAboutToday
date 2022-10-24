package com.phoenix.howabouttoday.room.dto;

import com.phoenix.howabouttoday.room.entity.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class RoomListDTO {

    private Long roomNum; // 객실 번호

    private String roomName; // 객실 이름

    private int defaultGuest; // 최소 인원

    private int maxGuest; // 최대 인원

    private LocalDate stayStartDate; // 객실 이용 시작일

    private LocalDate stayEndDate; // 객실 이용 종료일

    private int price; // 객실 가격

    private String roomInfo; // 객실 정보

    public RoomListDTO(Room room) {
        this.roomNum = room.getRoomNum();
        this.roomName = room.getRoomName();
        this.defaultGuest = room.getDefaultGuest();
        this.maxGuest = room.getMaxGuest();
        this.stayStartDate = room.getStayStartDate();
        this.stayEndDate = room.getStayEndDate();
        this.price = room.getPrice();
        this.roomInfo = room.getRoomInfo();
    }

}
