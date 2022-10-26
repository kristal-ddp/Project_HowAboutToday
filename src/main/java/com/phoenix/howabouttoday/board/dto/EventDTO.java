package com.phoenix.howabouttoday.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class EventDTO {

    // 게시판 작성, 수정 : Event

    private Long memberNum; // 회원번호

    @NotBlank(message = "제목을 입력하세요.")
    private String eventTitle; // 이벤트 게시글 제목

    private List<MultipartFile> eventImageList; // 이벤트 이미지
    private String date; // 이벤트 시작일 - 종료일

}
