package com.phoenix.howabouttoday.board.service;

import com.phoenix.howabouttoday.board.dto.*;
import com.phoenix.howabouttoday.board.entity.EventImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {

    // Board : Notice, About Us

    List<BoardListDTO> findAll_Board(String boardCategoryName); // 게시판 리스트
    BoardDetailDTO findOne_Board(Long boardNum); // 게시판 디테일
    void addBoard(BoardAddDTO boardAddDTO); // 게시글 작성

}
