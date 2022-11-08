package com.phoenix.howabouttoday.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class BoardCategory {

    // 게시판 : Notice, FAQ, About Us

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardCategoryNum; // 게시글 카테고리 번호

    @Column(nullable = false)
    private String boardCategoryName; // 게시글 카테고리 이름

    @Column(nullable = false)
    private Long boardParentNum; // 게시글 카테고리 상위 번호

    @OneToMany(mappedBy = "boardCategory", cascade = CascadeType.ALL)
    private List<Board> boardList = new ArrayList<>();

    @Builder
    public BoardCategory(String boardCategoryName, Long boardParentNum) {
        this.boardCategoryName = boardCategoryName;
        this.boardParentNum = boardParentNum;
    }
}
