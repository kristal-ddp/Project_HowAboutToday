package com.phoenix.howabouttoday.board.dto;

import com.phoenix.howabouttoday.board.entity.Board;
import com.phoenix.howabouttoday.board.entity.BoardCategory;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListDTO {

    private Long boardNum;
    private String boardTitle;
    private LocalDateTime boardCreate;

    public BoardListDTO(Board board) {
        this.boardNum = board.getBoardNum();
        this.boardTitle = board.getBoardTitle();
        this.boardCreate = board.getBoardCreate();
    }

}
