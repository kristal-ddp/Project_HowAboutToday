package com.phoenix.howabouttoday.board.entity;

import com.phoenix.howabouttoday.board.dto.CommentFormDTO;
import com.phoenix.howabouttoday.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNum; // 댓글 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_num")
    private Event event; // 이벤트 게시글 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private Member member; // 회원 번호

    @Column(nullable = false)
    private String commentContent; // 댓글 내용
    
    @CreatedDate
    @Column
    private LocalDateTime commentCreate; // 댓글 작성일

    // Event 댓글 작성
    public Comment(Event event, Member member, CommentFormDTO commentFormDTO) {
        this.event = event;
        this.member = member;
        this.commentContent = commentFormDTO.getCommentContent();
        this.commentCreate = LocalDateTime.now();
    }

    // Event 댓글 수정
    public void editComment(Long commentNum, CommentFormDTO commentFormDTO) {
        this.commentNum = commentNum;
        this.commentContent = commentFormDTO.getCommentContent();
    }

}
