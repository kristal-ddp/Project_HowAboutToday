package com.phoenix.howabouttoday.board.service;

import com.phoenix.howabouttoday.board.dto.*;

import java.util.List;

public interface FAQService {

    // Board : FAQ

    List<List<BoardDetailDTO>> findAll_FAQ(String boardCategoryName); // 게시판 리스트
    void addFAQ(FAQAddDTO faqAddDTO); // 게시글 작성

}
