package com.phoenix.howabouttoday.board.repository;

import com.phoenix.howabouttoday.board.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    // Event 게시판 디테일
    Optional<Event> findByEventNum(Long eventNum);

}