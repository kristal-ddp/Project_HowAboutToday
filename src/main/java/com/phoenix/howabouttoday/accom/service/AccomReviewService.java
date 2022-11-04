package com.phoenix.howabouttoday.accom.service;

import com.phoenix.howabouttoday.accom.dto.AccomReviewDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface AccomReviewService {

    List<AccomReviewDTO.ResponseDto> findAllByAccom(Long accomNum);

    Slice<AccomReviewDTO.ResponseDto> getAccomReviewList(Pageable pageable, Long accomNum);
}
