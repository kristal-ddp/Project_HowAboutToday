package com.phoenix.howabouttoday.room.entity;

import com.phoenix.howabouttoday.accom.entity.Accommodation;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;


import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RoomImage {
    
    //import가 import org.springframework.data.annotation.Id; 이거말고
    //import javax.persistence.*; 이걸로 되어야 기본키 에러가 안남
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long roomNum;


    @NotNull
    @Column(length = 50)
    private String roomOriginFileName;

    @NotNull
    @Column(length = 50)
    private String roomSaveFileName;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="room_roomNum", referencedColumnName = "roomNum")
    private Room room;

    @Builder
    public RoomImage(String roomOriginFileName,String roomSaveFileName,Room room) {
        this.roomOriginFileName = roomOriginFileName;
        this.roomSaveFileName = roomSaveFileName;
        this.room = room;
    }

}
