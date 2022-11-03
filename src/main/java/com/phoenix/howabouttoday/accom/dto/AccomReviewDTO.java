package com.phoenix.howabouttoday.accom.dto;


import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.room.entity.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class AccomReviewDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseDto {
        private Long accomReviewNum;//리뷰 번호
        private String accomMember;//작성자
        private double accomReviewRating;//리뷰 평점
        private LocalDate accomReviewCreate;//리뷰 작성일
        private String accomReviewContent;//리뷰 내용

        public ResponseDto(Review review) {
            this.accomMember = review.getMember().getNickname();
            this.accomReviewNum = review.getReviewNum();
            this.accomReviewRating = review.getReviewRating();
            this.accomReviewCreate = review.getReviewCreateDate();
            this.accomReviewContent = review.getReviewContent();

        }
    }
}
