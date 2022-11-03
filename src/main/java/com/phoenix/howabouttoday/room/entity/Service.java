package com.phoenix.howabouttoday.room.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long serviceNum;//서비스번호

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="room_roomNum")
    private Room room; //객실 번호

    @Enumerated(EnumType.STRING)
    private ServiceNames serviceName;//서비스이름

    @Builder
    public Service(Long serviceNum, ServiceNames serviceName) {
        this.serviceNum = serviceNum;
        this.serviceName = serviceName;
    }


}
