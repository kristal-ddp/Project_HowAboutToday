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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FAQServiceImpl implements FAQService {

    // Board : FAQ

    private final BoardRepository boardRepository;
    private final BoardCategoryRepository boardCategoryRepository;

    private final MemberRepository memberRepository;

    // 게시판 리스트
    @Override
    public List<List<BoardDetailDTO>> findAll_FAQ(String boardCategoryName) {

        List<BoardCategory> categoryList = boardCategoryRepository.findAllByCategoryName(boardCategoryName); // FAQ 관련 Category List
        List<List<BoardDetailDTO>> faqList = new ArrayList<>(); // Board Detail List

        // Board Detail List
        for(BoardCategory boardCategory : categoryList) { // Category 1개 : categoryList에서 1개씩 빼서 boardCategory에 넣는다

            // Board Detail 1개 
            // Entity → DTO
            List<BoardDetailDTO> lists = boardRepository.findAllByBoardCategory(boardCategory) // Entity List
                    .stream() // Entity Stream
                    .map(BoardDetailDTO::new) // DTO Stream
                    .collect(Collectors.toList()); // DTO List

            faqList.add(lists);
        }

        return faqList;
    }

    // 게시글 작성
    @Override
    @Transactional
    public void addFAQ(FAQAddDTO faqAddDTO) {

        Member member = memberRepository.findById(faqAddDTO.getMemberNum()).orElse(null);
        BoardCategory boardCategory = boardCategoryRepository.findById(faqAddDTO.getBoardCategoryNum()).orElse(null);

        Board board = new Board(member, boardCategory, faqAddDTO);
        boardRepository.save(board);

    }
}
