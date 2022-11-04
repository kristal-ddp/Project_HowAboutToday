package com.phoenix.howabouttoday.accom.repository;


import com.phoenix.howabouttoday.room.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccomReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findAllByRoom_Accommodation_AccomNum(Long accomNum);

    /** 숙소 상세페이지 리뷰 리스트 **/
    @EntityGraph(attributePaths = {"member","room"})
    Slice<Review> findAllByRoom_Accommodation_AccomNum(Pageable pageable, Long accomNum);
}
