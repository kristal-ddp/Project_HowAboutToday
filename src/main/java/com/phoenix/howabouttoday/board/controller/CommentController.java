package com.phoenix.howabouttoday.board.controller;

import com.phoenix.howabouttoday.board.dto.CommentFormDTO;
import com.phoenix.howabouttoday.board.service.CommentService;
import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 이벤트 게시판 댓글 작성
    @PostMapping("/event/{eventNum}/comments")
    public ResponseEntity commentAdd(@PathVariable Long eventNum, @RequestBody CommentFormDTO commentFormDTO,
                                   @LoginUser SessionDTO sessionDTO){

        return ResponseEntity.ok(commentService.addComment(sessionDTO.getNickname(), eventNum, commentFormDTO));

    }

    // 이벤트 게시판 댓글 수정
    @PutMapping("/event/{eventNum}/comments/{commentNum}")
    public ResponseEntity commentEdit(@PathVariable Long commentNum, @RequestBody CommentFormDTO commentFormDTO) {

        commentService.editComment(commentNum, commentFormDTO);
        return ResponseEntity.ok(commentNum);
    }

    // 이벤트 게시판 댓글 삭제
    @DeleteMapping("/event/{eventNum}/comments/{commentNum}")
    public ResponseEntity commentDelete(@PathVariable Long commentNum) {

        commentService.deleteComment(commentNum);
        return ResponseEntity.ok(commentNum);
    }
    
}
