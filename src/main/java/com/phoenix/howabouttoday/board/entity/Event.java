package com.phoenix.howabouttoday.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventNum; // 이벤트 게시글 번호

    @Column(nullable = false)
    private String eventTitle; // 이벤트 게시글 제목

    @CreatedDate
    @Column
    private LocalDateTime eventCreate; // 이벤트 게시일

    @Column(nullable = false)
    private LocalDate eventStart; // 이벤트 시작일

    @Column(nullable = false)
    private LocalDate eventEnd; // 이벤트 종료일

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventImage> eventImageList = new ArrayList<>();

    @Builder
    public Event(String eventTitle, LocalDateTime eventCreate, LocalDate eventStart, LocalDate eventEnd) {
        this.eventTitle = eventTitle;
        this.eventCreate = eventCreate;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

}
