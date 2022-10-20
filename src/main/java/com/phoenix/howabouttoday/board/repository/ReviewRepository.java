package com.phoenix.howabouttoday.board.repository;

import com.phoenix.howabouttoday.board.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
