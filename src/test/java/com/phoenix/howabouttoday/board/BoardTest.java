package com.phoenix.howabouttoday.board;

import com.phoenix.howabouttoday.board.dto.BoardDetailDTO;
import com.phoenix.howabouttoday.board.dto.BoardListDTO;
import com.phoenix.howabouttoday.board.dto.EventDetailDTO;
import com.phoenix.howabouttoday.board.entity.Board;
import com.phoenix.howabouttoday.board.entity.BoardCategory;
import com.phoenix.howabouttoday.board.entity.Event;
import com.phoenix.howabouttoday.board.entity.EventImage;
import com.phoenix.howabouttoday.board.repository.BoardCategoryRepository;
import com.phoenix.howabouttoday.board.repository.BoardRepository;
import com.phoenix.howabouttoday.board.repository.EventImageRepository;
import com.phoenix.howabouttoday.board.repository.EventRepository;
import com.phoenix.howabouttoday.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@Transactional
//@SpringBootTest
//@Rollback(false)
public class BoardTest {

//    @Autowired
//    BoardService boardService;
//    @Autowired
//    BoardRepository boardRepository;
//    @Autowired
//    BoardCategoryRepository boardCategoryRepository;
//    @Autowired
//    EventRepository eventRepository;
//    @Autowired
//    EventImageRepository eventImageRepository;
//
//    @Test
//    public void Board_Mapping_Test() {
//
//        // Board Category 입력
//        BoardCategory boardCategory = BoardCategory.builder()
//                .boardCategoryName("공지사항")
//                .boardParentNum(0)
//                .build();
//
//        // Board 게시글 입력
//        Board board = Board.builder()
//                .boardCategory(boardCategoryRepository.save(boardCategory))
//                .boardTitle("공지사항 테스트1")
//                .boardContent("공지사항 본문1")
//                .boardCreate(LocalDateTime.of(2022,10,12,16,51))
//                .build();
//
//        boardCategory.getBoardList().add(board); // boardCategory에 board 저장 --*
//        boardRepository.save(board); // boardRepository에 board 저장
//
//        List<Board> boards = boardRepository.findAll(); // boardLists 모두를 찾아서
//        Board board1 = boards.get(0); // 그 중 1번째(index1) board를 선택하고
//
//        assertThat(board1.getBoardTitle()).isEqualTo(board.getBoardTitle()); // 1번째 board의 Title과 입력한 board의 Title을 비교
//        assertThat(board1.getBoardContent()).isEqualTo(board.getBoardContent()); // Content 비교
//        assertThat(board1.getBoardCreate()).isEqualTo(board.getBoardCreate()); // Create date 비교
//        assertThat(board1.getBoardCategory().getBoardCategoryName()).isEqualTo(board.getBoardCategory().getBoardCategoryName()); // Category Name 비교
//        assertThat(board1.getBoardCategory().getBoardParentNum()).isEqualTo(board.getBoardCategory().getBoardParentNum()); // Parent Num 비교
//        assertThat(board1).isEqualTo(boardCategory.getBoardList().get(0)); // 1번째 board와 boardCategory에 저장된 board 비교 --*
//
//    }
//
//    @Test
//    public void Event_Mapping_Test() {
//
//        // Event 게시글 입력
//        Event event = Event.builder()
//                .eventTitle("이벤트 테스트1")
//                .eventCreate(LocalDateTime.of(2022,10,1,19,42))
//                .eventStart(LocalDate.of(2022,10,1))
//                .eventEnd(LocalDate.of(2022,10,16))
//                .build();
//
//        // Event image1 입력
//        EventImage image1 = EventImage.builder()
//                .event(event)
//                .originFileName("image1.jpg")
//                .saveFileName("image1.jpg")
//                .build();
//
//        // Event image2 입력
//        EventImage image2 = EventImage.builder()
//                .event(event)
//                .originFileName("image2.jpg")
//                .saveFileName("image2.jpg")
//                .build();
//
//        event.getEventImageList().add(image1); // event의 ImageList에 image1 저장
//        event.getEventImageList().add(image2); // event의 ImageList에 image2 저장
//        eventRepository.save(event); // eventRepository event 저장
//
//        List<Event> eventLists = eventRepository.findAll(); // eventList 모두를 찾아서
//        Event event1 = eventLists.get(0); // 그 중 1번째(index0) event를 선택하고
//
//        assertThat(event1.getEventTitle()).isEqualTo(event.getEventTitle()); // 1번째 event의 Title과 입력한 event의 Title을 비교
//        assertThat(event1.getEventCreate()).isEqualTo(event.getEventCreate()); // Create date 비교
//        assertThat(event1.getEventStart()).isEqualTo(event.getEventStart()); // Start date 비교
//        assertThat(event1.getEventEnd()).isEqualTo(event.getEventEnd()); // End date 비교
//        assertThat(event1.getEventImageList().size()).isEqualTo(2); // 1번째 event의 ImageList의 크기 비교
//
//    }
//
//    @Test
//    public void FindAll_Board_Test() {
//
//        // Board Category 입력
//        BoardCategory boardCategory = BoardCategory.builder()
//                .boardCategoryName("FAQ")
//                .boardParentNum(0)
//                .build();
//
//        // Board 게시글1 입력
//        Board board1 = Board.builder()
//                .boardCategory(boardCategoryRepository.save(boardCategory))
//                .boardTitle("FAQ 테스트1")
//                .boardContent("FAQ 본문1")
//                .boardCreate(LocalDateTime.of(2022,10,12,16,51))
//                .build();
//
//        // Board 게시글2 입력
//        Board board2 = Board.builder()
//                .boardCategory(boardCategoryRepository.save(boardCategory))
//                .boardTitle("FAQ 테스트2")
//                .boardContent("FAQ 본문2")
//                .boardCreate(LocalDateTime.of(2022,10,17,16,51))
//                .build();
//
//        boardCategory.getBoardList().add(board1);
//        boardCategory.getBoardList().add(board2);
//        boardCategoryRepository.save(boardCategory);
//
//        String boardCategoryName = boardCategory.getBoardCategoryName();
//        List<BoardListDTO> boardLists = boardService.findAll_Board(boardCategoryName);
//
//        assertThat(boardLists.size()).isEqualTo(2);
//        assertThat(boardLists.get(0).getBoardTitle()).isEqualTo("FAQ 테스트1");
//    }
//
//    @Test
//    public void findOne_Board_Test() {
//
//        // Board Category 입력
//        BoardCategory boardCategory = BoardCategory.builder()
//                .boardCategoryName("FAQ")
//                .boardParentNum(0)
//                .build();
//
//        // Board 게시글 입력
//        Board board = Board.builder()
//                .boardCategory(boardCategoryRepository.save(boardCategory))
//                .boardTitle("FAQ 테스트1")
//                .boardContent("FAQ 본문1")
//                .boardCreate(LocalDateTime.of(2022,10,12,16,51))
//                .build();
//
//        boardCategory.getBoardList().add(board);
//        boardRepository.save(board);
//
//        Long boardNum = board.getBoardNum();
//        BoardDetailDTO board1 = boardService.findOne_Board(boardNum);
//
//        assertThat(board1.getBoardTitle()).isEqualTo("FAQ 테스트1");
//
//    }
//
//    @Test
//    public void findOne_Event_Test() {
//
//        // Event 게시글 입력
//        Event event = Event.builder()
//                .eventTitle("이벤트 테스트1")
//                .eventCreate(LocalDateTime.of(2022,10,1,19,42))
//                .eventStart(LocalDate.of(2022,10,1))
//                .eventEnd(LocalDate.of(2022,10,15))
//                .build();
//
//        // Event image
//        EventImage image1 = EventImage.builder()
//                .event(event)
//                .originFileName("image0.jpg")
//                .saveFileName("image0.jpg")
//                .build();
//        EventImage image2 = EventImage.builder()
//                .event(event)
//                .originFileName("image1.jpg")
//                .saveFileName("image1.jpg")
//                .build();
//
//        event.getEventImageList().add(image1);
//        event.getEventImageList().add(image2);
//
//        eventRepository.save(event);
//
//        Long eventNum = event.getEventNum();
//        EventDetailDTO event1 = boardService.findOne_Event(eventNum);
//
//        assertThat(event1.getEventTitle()).isEqualTo(event.getEventTitle()); // event1과 입력한 event의 Title 비교
//        assertThat(event1.getEventStart()).isEqualTo(event.getEventStart()); // Start date 비교
//        assertThat(event1.getEventEnd()).isEqualTo(event.getEventEnd()); // End date 비교
//        assertThat(event1.getEventImageList().size()).isEqualTo(2); // event1의 ImageList 크기 비교
//
//    }

}
