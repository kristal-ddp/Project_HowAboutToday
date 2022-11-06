package com.phoenix.howabouttoday.board.service;

import com.phoenix.howabouttoday.board.dto.*;
import com.phoenix.howabouttoday.board.entity.Board;
import com.phoenix.howabouttoday.board.entity.BoardCategory;
import com.phoenix.howabouttoday.board.repository.BoardCategoryRepository;
import com.phoenix.howabouttoday.board.repository.BoardRepository;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    // Board : Notice, About Us

    private final BoardRepository boardRepository;
    private final BoardCategoryRepository boardCategoryRepository;

    private final MemberRepository memberRepository;

    // 게시판 리스트 (모든 게시글 조회)
    @Override
    public Slice<BoardListDTO> findAll_Board(String boardCategoryName, Pageable pageable) {

        // Entity → DTO
        Slice<BoardListDTO> lists = boardRepository.findAllByCategoryName(boardCategoryName, pageable) // Entity
                .map(BoardListDTO::new); // DTO

        return lists;
    }

    // 게시판 디테일 (게시글 1개 조회)
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
    public void addBoard(BoardFormDTO boardFormDTO) {

        Member member = memberRepository.findById(boardFormDTO.getMemberNum()).orElse(null);
        BoardCategory boardCategory = boardCategoryRepository.findById(boardFormDTO.getBoardCategoryNum()).orElse(null);

        Board board = new Board(member, boardCategory, boardFormDTO);
        boardRepository.save(board);
    }

    // 게시글 수정
    @Override
    @Transactional
    public void editBoard(Long boardNum, BoardFormDTO boardFormDTO) {

        Board board = boardRepository.findById(boardNum).orElse(null);
        board.editBoard(board.getBoardNum(), boardFormDTO);
    }

    // 게시글 삭제
    @Override
    @Transactional
    public void deleteBoard(BoardDetailDTO boardDetailDTO) {

        Board board = boardRepository.findById(boardDetailDTO.getBoardNum()).orElse(null);
        boardRepository.delete(board);
    }

}
