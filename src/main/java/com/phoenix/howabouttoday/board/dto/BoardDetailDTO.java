package com.phoenix.howabouttoday.board.dto;

import com.phoenix.howabouttoday.board.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class BoardDetailDTO {

    // 게시판 리스트 페이지 : FAQ
    // 게시판 디테일 페이지 : Notice, About Us

    private Long boardNum; // 게시글 번호
    private String boardTitle; // 게시글 제목
    private String boardContent; // 게시글 내용
    private LocalDateTime boardCreate; // 게시일

    private String boardCategoryName; // 게시글 카테고리 이름

    public BoardDetailDTO(Board board) {
        this.boardNum = board.getBoardNum();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.boardCreate = board.getBoardCreate();
        this.boardCategoryName = board.getBoardCategory().getBoardCategoryName();
    }

}
