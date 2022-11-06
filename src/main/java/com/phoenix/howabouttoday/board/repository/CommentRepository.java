package com.phoenix.howabouttoday.board.repository;

import com.phoenix.howabouttoday.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
