package com.phoenix.howabouttoday.payment.dto;

import com.phoenix.howabouttoday.payment.enumType.ReviewResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomReviewCreateResponseDTO {

    private ReviewResponseCode code;
    private String name;
    private String content;

    public RoomReviewCreateResponseDTO(ReviewResponseCode code) {
        this.code = code;
    }
}
