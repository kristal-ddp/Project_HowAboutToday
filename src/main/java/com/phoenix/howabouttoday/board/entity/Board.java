package com.phoenix.howabouttoday.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
public class Board {

    // 게시판 : Notice, FAQ, About Us

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNum; // 게시글 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_category_num")
    private BoardCategory boardCategory; // 게시글 카테고리 번호

    @Column(nullable = false)
    private String boardTitle; // 게시글 제목

    @Column(nullable = false)
    private String boardContent; // 게시글 내용

    @CreatedDate
    @Column
    private LocalDateTime boardCreate; // 게시일

    @Builder
    public Board(BoardCategory boardCategory, String boardTitle, String boardContent, LocalDateTime boardCreate) {
        this.boardCategory = boardCategory;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardCreate = boardCreate;
    }

}
