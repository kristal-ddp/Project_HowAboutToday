package com.phoenix.howabouttoday.board.service;

import com.phoenix.howabouttoday.board.dto.BoardDetailDTO;
import com.phoenix.howabouttoday.board.dto.BoardListDTO;
import com.phoenix.howabouttoday.board.dto.EventDetailDTO;
import com.phoenix.howabouttoday.board.dto.EventListDTO;
import com.phoenix.howabouttoday.board.entity.BoardCategory;
import com.phoenix.howabouttoday.board.repository.BoardCategoryRepository;
import com.phoenix.howabouttoday.board.repository.BoardRepository;
import com.phoenix.howabouttoday.board.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardCategoryRepository boardCategoryRepository;
    private final EventRepository eventRepository;

    // (Notice, About Us) 게시판 리스트
    @Override
    public List<BoardListDTO> findAll_Board(String boardCategoryName) {

        // Entity → DTO
        List<BoardListDTO> lists = boardRepository.findAllByCategoryName(boardCategoryName) // Entity List
                .stream() // Entity Stream
                .map(BoardListDTO::new) // DTO Stream
                .collect(Collectors.toList()); // DTO List

        return lists;
    }

    // FAQ 게시판 리스트
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

    // Event 게시판 리스트
    @Override
    public List<EventListDTO> findAll_Event() {

        // Entity → DTO
        List<EventListDTO> lists = eventRepository.findAll() // Entity List
                .stream() // Entity Stream
                .map(EventListDTO::new) // DTO Stream
                .collect(Collectors.toList()); // DTO List

        return lists;
    }

    // (Notice, About Us) 게시판 디테일
    @Override
    public BoardDetailDTO findOne_Board(Long boardNum) {

        // Entity → DTO
        return boardRepository.findByBoardNum(boardNum) // Entity
                .map(BoardDetailDTO::new) // DTO
                .orElse(null); // 에러 시 null 반환
    }

    // Event 게시판 디테일
    @Override
    public EventDetailDTO findOne_Event(Long eventNum) {

        // Entity → DTO
        return eventRepository.findByEventNum(eventNum) // Entity
                .map(EventDetailDTO::new) // DTO
                .orElse(null); // 에러 시 null 반환
    }

}
