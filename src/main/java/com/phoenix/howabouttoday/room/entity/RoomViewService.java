package com.phoenix.howabouttoday.room.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Builder
public class RoomViewService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long roomViewServiceNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_num", referencedColumnName = "roomNum")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_num")
    private Service service;

    @Column
    private String serviceName;

    @Builder
    public RoomViewService(Room room, Service service, String serviceName) {
        this.service = service;
        this.room = room;
        this.serviceName = serviceName;
    }

}
