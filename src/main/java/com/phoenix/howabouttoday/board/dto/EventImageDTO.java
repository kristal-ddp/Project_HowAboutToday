package com.phoenix.howabouttoday.board.dto;

import com.phoenix.howabouttoday.board.entity.EventImage;
import lombok.Getter;

@Getter
public class EventImageDTO {

    private Long eventImageNum; // 이벤트 이미지 번호
    private Long eventNum; // 이벤트 게시글 번호
    private String originFileName; // 기존 파일 이름
    private String saveFileName; // 저장 파일 이름

    public EventImageDTO(EventImage eventImage) {
        this.eventImageNum = eventImage.getEventImageNum();
        this.eventNum = eventImage.getEvent().getEventNum();
        this.originFileName = eventImage.getOriginFileName();
        this.saveFileName = eventImage.getSaveFileName();
    }

}
