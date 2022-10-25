package com.phoenix.howabouttoday.board.repository;

import com.phoenix.howabouttoday.board.entity.Board;
import com.phoenix.howabouttoday.board.entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // (Notice, About Us) 게시판 리스트
    @Query(value = "select b.* from board b join board_category c " +
            "on b.board_category_num = c.board_category_num " +
            "where c.board_category_name = ?1", nativeQuery = true)
    List<Board> findAllByCategoryName(String boardCategoryName);

    // FAQ 게시판 리스트 관련
    // Board Category로 Board Entity의 모든 것을 찾는다
    List<Board> findAllByBoardCategory(BoardCategory boardCategory);

}
