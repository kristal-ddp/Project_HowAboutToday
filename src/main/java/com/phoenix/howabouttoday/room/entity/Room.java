package com.phoenix.howabouttoday.room.entity;

import com.phoenix.howabouttoday.accom.entity.AccomImage;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.payment.AvailableDate;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long roomNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="accommodation_accomNum", referencedColumnName = "accomNum")
    private Accommodation accommodation;

    @NotNull
    @Column(length = 50)
    private String roomName;

    private Integer defaultGuest;//최소 인원

    private Integer maxGuest;//최대 인원

    @NotNull
    private Integer price;//주말 숙소 금액

    private String roomInfo;//객실 정보

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomImage> roomImage;


    //양방향 매핑을 위해 추가
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AvailableDate> availableDate = new ArrayList<>();

    @Builder
    public Room(String roomName,int defaultGuest,int maxGuest, Integer price, String roomInfo, Accommodation accommodation) {
        this.roomName = roomName;
        this.defaultGuest = defaultGuest;
        this.maxGuest = maxGuest;
        this.price = price;
        this.roomInfo = roomInfo;
        this.accommodation = accommodation;
    }
}
