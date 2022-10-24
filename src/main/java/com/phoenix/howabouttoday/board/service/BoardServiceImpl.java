package com.phoenix.howabouttoday.board.service;

import com.phoenix.howabouttoday.board.dto.*;
import com.phoenix.howabouttoday.board.entity.Board;
import com.phoenix.howabouttoday.board.entity.BoardCategory;
import com.phoenix.howabouttoday.board.repository.BoardCategoryRepository;
import com.phoenix.howabouttoday.board.repository.BoardRepository;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    // Board : Notice, About Us

    private final BoardRepository boardRepository;
    private final BoardCategoryRepository boardCategoryRepository;

    private final MemberRepository memberRepository;

    // 게시판 리스트
    @Override
    public List<BoardListDTO> findAll_Board(String boardCategoryName) {

        // Entity → DTO
        List<BoardListDTO> lists = boardRepository.findAllByCategoryName(boardCategoryName) // Entity List
                .stream() // Entity Stream
                .map(BoardListDTO::new) // DTO Stream
                .collect(Collectors.toList()); // DTO List

        return lists;
    }

    // 게시판 디테일
    @Override
    public BoardDetailDTO findOne_Board(Long boardNum) {

        // Entity → DTO
        return boardRepository.findById(boardNum) // Entity
                .map(BoardDetailDTO::new) // DTO
                .orElse(null); // 에러 시 null 반환
    }

    // 게시글 작성
    @Override
    @Transactional
    public void addBoard(BoardAddDTO boardAddDTO) {

        Member member = memberRepository.findById(boardAddDTO.getMemberNum()).orElse(null);
        BoardCategory boardCategory = boardCategoryRepository.findById(boardAddDTO.getBoardCategoryNum()).orElse(null);

        Board board = new Board(member, boardCategory, boardAddDTO);
        boardRepository.save(board);
    }

}
