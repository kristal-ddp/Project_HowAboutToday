package com.phoenix.howabouttoday.room.entity;

import com.phoenix.howabouttoday.accom.entity.Accommodation;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;


import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RoomImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomImageNum; //객실 이미지 번호

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="room_roomNum")
    private Room room; //객실 번호

    @NotNull
    @Column(length = 50)
    private String roomOriginFileName; //기존 파일 이름

    @NotNull
    @Column(length = 50)
    private String roomSaveFileName; //저장 파일 이름

    @Builder
    public RoomImage(String roomOriginFileName,String roomSaveFileName,Room room) {
        this.roomOriginFileName = roomOriginFileName;
        this.roomSaveFileName = roomSaveFileName;
        this.room = room;
    }

}
