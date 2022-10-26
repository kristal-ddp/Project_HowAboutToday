package com.phoenix.howabouttoday.board.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FAQDTO {

    // 게시판 작성, 수정 : FAQ

    private Long memberNum; // 회원번호
    private Long boardCategoryNum; // 게시글 카테고리 번호

    @NotBlank(message = "제목을 입력하세요.")
    private String boardTitle; // 게시글 제목

    @NotBlank(message = "내용을 입력하세요.")
    private String boardContent; // 게시글 내용

}
