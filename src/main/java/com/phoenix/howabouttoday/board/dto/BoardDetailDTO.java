package com.phoenix.howabouttoday.board.dto;

import com.phoenix.howabouttoday.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDetailDTO {

    private Long boardNum;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime boardCreate;

    public BoardDetailDTO(Board board) {
        this.boardNum = board.getBoardNum();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.boardCreate = board.getBoardCreate();
    }

}
