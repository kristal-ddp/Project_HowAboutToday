package com.phoenix.howabouttoday.room.dto;

import com.phoenix.howabouttoday.room.entity.Room;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long availableDataId;

    @Column
    private LocalDate date;

//    // 양방향
//    @OneToMany(mappedBy = "availableDate", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<AvailableTime> availableTimes = new ArrayList<>();

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="roomNum"/*, referencedColumnName = "roomNum"*/)
    private Room room;

    @Builder
    public AvailableDate(LocalDate date,Room room){
        this.date=date;
        this.room=room;
    }
}







