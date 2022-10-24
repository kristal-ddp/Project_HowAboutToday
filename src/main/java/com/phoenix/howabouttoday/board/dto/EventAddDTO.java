package com.phoenix.howabouttoday.board.dto;

import com.phoenix.howabouttoday.board.entity.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class EventAddDTO {

    // 게시판 작성 페이지 : Event

    private Long memberNum; // 회원번호

    @NotBlank(message = "제목을 입력하세요.")
    private String eventTitle; // 이벤트 게시글 제목

    private String date;

}
