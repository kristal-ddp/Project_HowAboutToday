package com.phoenix.howabouttoday.accom.service;

import com.phoenix.howabouttoday.accom.dto.AccomReviewDTO;

import java.util.List;

public interface AccomReviewService {

    List<AccomReviewDTO.ResponseDto> findAllByAccom(Long accomNum);
}
