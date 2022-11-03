package com.phoenix.howabouttoday.room.dto;

import com.phoenix.howabouttoday.room.entity.Review;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class RoomReviewDTO {

    private Long roomNum;
    private Long roomReviewNum;

    private String nickname;

    private LocalDate reviewCreatedDate; //리뷰 작성일

    private String reviewContent; // 리뷰 내용

    private Double reviewRating;

    public RoomReviewDTO(Review review) {

        this.roomNum = review.getRoom().getRoomNum();
        this.roomReviewNum = review.getReviewNum();
        this.nickname = review.getMember().getNickname();
        this.reviewCreatedDate = review.getReviewCreateDate();
        this.reviewContent = review.getReviewContent();
        this.reviewRating = review.getReviewRating();

    }

}
