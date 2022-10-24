package com.phoenix.howabouttoday.room.dto;

import com.phoenix.howabouttoday.room.entity.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class RoomDetailDTO {

    private Long roomNum; // 객실 번호

    private String roomName; // 객실 이름

    private Integer defaultGuest; // 최소 인원

    private Integer maxGuest; // 최대 인원

    private Double roomRating;

    private Integer roomReviewNum;

    private LocalDate stayStartDate; // 객실 이용 시작일

    private LocalDate stayEndDate; // 객실 이용 종료일

    private Integer price; // 객실 가격

    private List<RoomImageDTO> roomImageList; //객실 이미지

    public RoomDetailDTO(Room room) {
        this.roomRating = room.getRoomRating();
        this.roomReviewNum = room.getRoomReviewNum();
        this.roomNum = room.getRoomNum();
        this.roomName = room.getRoomName();
        this.defaultGuest = room.getDefaultGuest();
        this.maxGuest = room.getMaxGuest();
        this.stayStartDate = room.getStayStartDate();
        this.stayEndDate = room.getStayEndDate();
        this.roomImageList = room.getRoomImageList().stream().map(RoomImageDTO::new).collect(Collectors.toList());
    }
}
