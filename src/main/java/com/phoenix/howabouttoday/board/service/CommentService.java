package com.phoenix.howabouttoday.board.service;

import com.phoenix.howabouttoday.board.dto.CommentFormDTO;

public interface CommentService {

    // Comment : Event 게시판

    Long addComment(String nickname, Long eventNum, CommentFormDTO commentFormDTO); // 이벤트 게시판 댓글 작성
    void editComment(Long commentNum, CommentFormDTO commentFormDTO); // 이벤트 게시판 댓글 수정
    void deleteComment(Long commentNum); // 이벤트 게시판 댓글 삭제

}
