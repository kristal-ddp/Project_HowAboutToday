package com.phoenix.howabouttoday.board.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardImageNum; // 게시글 이미지 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_num")
    private Board board; // 게시글 번호

    @Column(nullable = false)
    private String boardOriginFileName; // 게시글 이미지 기존 파일 이름

    @Column(nullable = false)
    private String boardSaveFileName; // 게시글 이미지 저장 파일 이름

    @Builder
    public BoardImage(Board board, String boardOriginFileName, String boardSaveFileName) {
        this.board = board;
        this.boardOriginFileName = boardOriginFileName;
        this.boardSaveFileName = boardSaveFileName;
    }

}
