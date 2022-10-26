package com.phoenix.howabouttoday.board.service;

import com.phoenix.howabouttoday.board.dto.*;

import java.util.List;

public interface FAQService {

    // Board : FAQ

    List<List<BoardDetailDTO>> findAll_FAQ(String boardCategoryName); // 게시판 리스트 (모든 게시글 조회)
    BoardDetailDTO findOne_FAQ(Long boardNum); // 게시글 1개 조회

    void addFAQ(FAQDTO FAQDTO); // 게시글 작성
    void editFAQ(Long boardNum, FAQDTO faqDTO); // 게시글 수정
    void deleteFAQ(BoardDetailDTO boardDetailDTO); // 게시글 삭제
}
