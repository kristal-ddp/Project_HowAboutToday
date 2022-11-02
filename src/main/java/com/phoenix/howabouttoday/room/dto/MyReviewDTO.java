package com.phoenix.howabouttoday.room.dto;


import com.phoenix.howabouttoday.room.entity.Review;
import lombok.Getter;

@Getter
public class MyReviewDTO {
    private Long reviewNum;
    private Long roomNum;
    private String accomName;
    private String roomName;
    private String createdDate;
    private Double reviewRating;
    private String reviewContent;


    public MyReviewDTO(Review review) {
        this.reviewNum = review.getReviewNum();
        this.roomNum = review.getRoom().getRoomNum();
        this.accomName = review.getRoom().getAccommodation().getAccomName();
        this.roomName = review.getRoom().getRoomName();
        this.createdDate = review.getReviewCreateDate().toString();
        this.reviewRating = review.getReviewRating();
        this.reviewContent = review.getReviewContent();
    }
}

