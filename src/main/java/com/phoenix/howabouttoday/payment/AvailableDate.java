package com.phoenix.howabouttoday.payment;

import com.phoenix.howabouttoday.room.entity.Room;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String date;

//    // 양방향
//    @OneToMany(mappedBy = "availableDate", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<AvailableTime> availableTimes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="roomNum"/*, referencedColumnName = "roomNum"*/)
    private Room room;

    @Builder
    public AvailableDate(String date,Room room){
        this.date=date;
        this.room=room;
    }
}







