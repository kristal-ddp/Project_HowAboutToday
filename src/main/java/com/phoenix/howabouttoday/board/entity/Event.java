package com.phoenix.howabouttoday.board.entity;

import com.phoenix.howabouttoday.board.dto.EventAddDTO;
import com.phoenix.howabouttoday.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private Member member; // 회원 번호

    @Column(nullable = false)
    private String eventTitle; // 이벤트 게시글 제목

    @CreatedDate
    @Column
    private LocalDate eventCreate; // 이벤트 게시일

    @Column(nullable = false)
    private LocalDate eventStart; // 이벤트 시작일

    @Column(nullable = false)
    private LocalDate eventEnd; // 이벤트 종료일

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventImage> eventImageList = new ArrayList<>();

    // Event 게시글 작성
    public Event(Member member, EventAddDTO eventAddDTO) {

        String[] splitDate = eventAddDTO.getDate().split(" - ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        this.member = member;
        this.eventTitle = eventAddDTO.getEventTitle();
        this.eventCreate = LocalDate.now();
        this.eventStart = LocalDate.parse(splitDate[0], formatter);
        this.eventEnd = LocalDate.parse(splitDate[1], formatter);
    }

}
