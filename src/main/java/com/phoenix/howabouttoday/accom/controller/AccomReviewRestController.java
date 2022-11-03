package com.phoenix.howabouttoday.accom.controller;


import com.phoenix.howabouttoday.accom.dto.AccomReviewDTO;
import com.phoenix.howabouttoday.accom.service.AccomReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/single")
@RequiredArgsConstructor
public class AccomReviewRestController {

    private final AccomReviewService accomReviewService;
    @GetMapping("/{accomNum}")
    public Slice<AccomReviewDTO.ResponseDto> accomReviews(@PathVariable(required = false) Long accomNum,
                                                          @PageableDefault(page = 0, size = 3, sort = "reviewNum", direction = Sort.Direction.DESC) Pageable pageable) {

        return accomReviewService.getAccomReviewList(pageable, accomNum);
    }
}
