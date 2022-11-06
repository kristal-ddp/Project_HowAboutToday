package com.phoenix.howabouttoday.board.dto;

import com.phoenix.howabouttoday.board.entity.Comment;
import com.phoenix.howabouttoday.member.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CommentListDTO {

    // 이벤트 게시판 댓글 리스트 : Comment

    private Long commentNum; // 댓글 번호
    private Long eventNum; // 이벤트 게시글 번호
    private String nickname; // 회원 닉네임
    private String email; // 회원 이메일
    private Role role; // 회원 등급

    private String commentContent; // 댓글 내용
    private LocalDateTime commentCreate; // 댓글 작성일

    public CommentListDTO(Comment comment) {
        this.commentNum = comment.getCommentNum();
        this.eventNum = comment.getEvent().getEventNum();
        this.nickname = comment.getMember().getNickname();
        this.email = comment.getMember().getEmail();
        this.role = comment.getMember().getRole();
        this.commentContent = comment.getCommentContent();
        this.commentCreate = comment.getCommentCreate();
    }

}
