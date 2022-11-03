package com.phoenix.howabouttoday.accom.repository;


import com.phoenix.howabouttoday.room.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccomReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findAllByRoom_Accommodation_AccomNum(Long accomNum);

}
