package com.phoenix.howabouttoday.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class EventEditDTO {

    // 게시판 작성 페이지 : Event

    private Long memberNum; // 회원번호

    @NotBlank(message = "제목을 입력하세요.")
    private String eventTitle; // 이벤트 게시글 제목

    private String date;
//    private LocalDate eventStart; // 이벤트 시작일
//    private LocalDate eventEnd; // 이벤트 종료일
//    private List<EventImageDTO> eventImageList; // 이벤트 이미지

//    public LocalDate getEventStart() {
//        return LocalDate.parse(date.split("-")[0]);
//    }
//
//    public LocalDate getEventEnd() {
//        return LocalDate.parse(date.split("-")[1]);
//    }
}
