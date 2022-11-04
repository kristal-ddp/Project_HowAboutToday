package com.phoenix.howabouttoday.board.dto;

import com.phoenix.howabouttoday.board.entity.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class EventDetailDTO {

    // 게시판 리스트 페이지 : Notice, FAQ, About Us

    private Long memberNum; // 회원번호
    private Long eventNum; // 이벤트 게시글 번호
    private String eventTitle; // 이벤트 게시글 제목
    private LocalDate eventCreate; // 이벤트 게시일
    private String date; // 이벤트 시작일 - 종료일
    private List<EventImageDTO> eventImageList; // 이벤트 이미지

    public EventDetailDTO(Event event) {
        this.memberNum = event.getMember().getMemberNum();
        this.eventNum = event.getEventNum();
        this.eventTitle = event.getEventTitle();
        this.eventCreate = event.getEventCreate();
        this.date = event.getEventStart().toString().replaceAll("-","/")+ " - "
                + event.getEventEnd().toString().replaceAll("-","/");
        this.eventImageList = event.getEventImageList()
                .stream()
                .map(EventImageDTO::new)
                .collect(Collectors.toList());
    }

}
