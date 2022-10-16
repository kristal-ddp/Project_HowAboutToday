package com.phoenix.howabouttoday.board.repository;

import com.phoenix.howabouttoday.board.entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {

}