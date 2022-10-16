package com.phoenix.howabouttoday.board.service;

import com.phoenix.howabouttoday.board.dto.BoardDetailDTO;
import com.phoenix.howabouttoday.board.dto.BoardListDTO;
import com.phoenix.howabouttoday.board.entity.Board;
import com.phoenix.howabouttoday.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시판 리스트 페이지
    @Transactional(readOnly = true)
    public List<BoardListDTO> boardList() {
        return boardRepository.findAllDesc().stream()
                .map(BoardListDTO::new)
                .collect(Collectors.toList());
    }

    // 게시판 디테일 페이지
    @Transactional(readOnly = true)
    public BoardDetailDTO boardDetail(Long boardNum) {
        Board board = boardRepository.findById(boardNum).orElseThrow(()
                -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        return new BoardDetailDTO(board);
    }

}
