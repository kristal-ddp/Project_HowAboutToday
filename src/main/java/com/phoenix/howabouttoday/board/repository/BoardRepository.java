package com.phoenix.howabouttoday.board.repository;

import com.phoenix.howabouttoday.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // 방법 1
//    List<Board> findAllByOrderByBoardNumDesc();

    // 방법 2
    @Query("select a from Board a order by a.boardNum desc")
    List<Board> findAllDesc();

}