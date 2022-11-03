package com.phoenix.howabouttoday.room.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long availableDateId;

    @Column
    private LocalDate oneDay;

//    // 양방향
//    @OneToMany(mappedBy = "availableDate", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<AvailableTime> availableTimes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="roomNum"/*, referencedColumnName = "roomNum"*/)
    private Room room;

    @Builder
    public AvailableDate(LocalDate date,Room room){
        this.oneDay =date;
        this.room=room;
    }
}







