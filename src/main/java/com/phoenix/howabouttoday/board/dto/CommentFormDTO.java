package com.phoenix.howabouttoday.board.dto;

import com.phoenix.howabouttoday.board.entity.Event;
import com.phoenix.howabouttoday.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CommentFormDTO {

    // 이벤트 게시판 댓글 작성, 수정 : Comment

    private Long commentNum; // 댓글 번호
    private Member member; // 회원
    private Event event; // 이벤트

    @NotBlank(message = "댓글을 입력하세요.")
    private String commentContent; // 댓글 내용

}
