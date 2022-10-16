package com.phoenix.howabouttoday.board;

import com.phoenix.howabouttoday.board.entity.Board;
import com.phoenix.howabouttoday.board.entity.BoardCategory;
import com.phoenix.howabouttoday.board.entity.BoardImage;
import com.phoenix.howabouttoday.board.repository.BoardCategoryRepository;
import com.phoenix.howabouttoday.board.repository.BoardImageRepository;
import com.phoenix.howabouttoday.board.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(false)
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardCategoryRepository boardCategoryRepository;
    @Autowired
    BoardImageRepository boardImageRepository;

    @Test
    public void Board_Mapping_Test() {

        BoardCategory category = BoardCategory.builder()
                .boardCategoryName("공지사항")
                .boardParentNum(0)
                .build();

        BoardCategory saveCategory = boardCategoryRepository.save(category);

        Board board = Board.builder()
                .boardTitle("공지사항 테스트")
                .boardContent("공지사항 본문")
                .boardCreate(LocalDateTime.of(2022,10,12,16,51))
                .boardCategory(saveCategory)
                .build();

        BoardImage image = BoardImage.builder()
                .boardOriginFileName("image1.jpg")
                .boardSaveFileName("image1.jpg")
                .board(board)
                .build();

        category.getBoardList().add(board);
        board.getBoardImageList().add(image);

        boardRepository.save(board);

        List<Board> boardList = boardRepository.findAll();

        Board board1 = boardList.get(0);
        assertThat(board1.getBoardTitle()).isEqualTo(board.getBoardTitle());
        assertThat(board1.getBoardContent()).isEqualTo(board.getBoardContent());
        assertThat(board1.getBoardCreate()).isEqualTo(board.getBoardCreate());
        assertThat(board1.getBoardCategory().getBoardCategoryName()).isEqualTo(board.getBoardCategory().getBoardCategoryName());
        assertThat(board1.getBoardCategory().getBoardParentNum()).isEqualTo(board.getBoardCategory().getBoardParentNum());

    }

}
