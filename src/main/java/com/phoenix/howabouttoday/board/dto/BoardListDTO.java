package com.phoenix.howabouttoday.board.dto;

import com.phoenix.howabouttoday.board.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class BoardListDTO {

    // 게시판 리스트 페이지 : Notice, About Us

    private Long boardNum; // 게시글 번호
    private String boardTitle; // 게시글 제목
    private LocalDateTime boardCreate; // 게시일

    public BoardListDTO(Board board) {
        this.boardNum = board.getBoardNum();
        this.boardTitle = board.getBoardTitle();
        this.boardCreate = board.getBoardCreate();
    }

}
