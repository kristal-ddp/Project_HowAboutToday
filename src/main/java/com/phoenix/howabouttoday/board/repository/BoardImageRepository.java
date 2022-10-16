package com.phoenix.howabouttoday.board.repository;

import com.phoenix.howabouttoday.board.entity.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {

}