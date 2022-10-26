package com.phoenix.howabouttoday.accom.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.phoenix.howabouttoday.accom.entity.AccomCategory;
import com.phoenix.howabouttoday.accom.entity.AccomImage;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.entity.Region;
import com.phoenix.howabouttoday.room.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class AccomDto {



    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponsePageDto{

        private Long accomNum;  //숙소 번호

        private String accomName;//숙소 이름

        private AccomCategory accomCategory;

        private String accomAddress;//숙소 주소

        private Double accomRating;//숙소 평점

        private Integer totalReviewNum;//숙소의 평점 수

        private Integer lowPrice; //숙소의 객실 최저가

        private AccomImage accommodationImage;//숙소의 대표 이미지

        public ResponsePageDto(Accommodation accommodation) {
            this.accomName = accommodation.getAccomName();
            this.accomCategory = accommodation.getAccomCategory();
            this.accomAddress = accommodation.getAccomAddress();
            this.accomRating = accommodation.getAccomRating();
            this.totalReviewNum = accommodation.getTotalReviewNum();
            this.accomNum = accommodation.getAccomNum();
            this.accommodationImage = accommodation.getAccommodationImage().get(0);
            this.lowPrice = accommodation.getLowPrice();
        }
    }



}
