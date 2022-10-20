package com.phoenix.howabouttoday.board.repository;

import com.phoenix.howabouttoday.board.entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {

    // FAQ 게시판 리스트
    @Query(value = "select c1.* from board_category c1 join board_category c2 " +
            "on c1.board_parent_num = c2.board_category_num " +
            "where c2.board_category_name = ?1", nativeQuery = true)
    List<BoardCategory> findAllByCategoryName(String boardCategoryName);

}
