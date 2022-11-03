package com.phoenix.howabouttoday.board.dto;

import com.phoenix.howabouttoday.board.entity.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class EventListDTO {

    private Long eventNum; // 이벤트 게시글 번호
    private String eventTitle; // 이벤트 게시글 제목
    private LocalDate eventCreate; // 이벤트 게시일
    private String date; // 이벤트 시작일 - 종료일

    public EventListDTO(Event event) {
        this.eventNum = event.getEventNum();
        this.eventTitle = event.getEventTitle();
        this.eventCreate = event.getEventCreate();
        this.date = event.getEventStart().toString().replaceAll("-","/")+ " - "
                + event.getEventEnd().toString().replaceAll("-","/");
    }

}
