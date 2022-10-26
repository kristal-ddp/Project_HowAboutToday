package com.phoenix.howabouttoday.accom.controller;

import com.phoenix.howabouttoday.accom.dto.AccomDto;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.service.AccomodationService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/accom")
@RequiredArgsConstructor
public class AccomRestController {



    private final AccomodationService accomodationService;

    @GetMapping("/{category_name}")
    public Slice<AccomDto.ResponsePageDto> accommodations(@PathVariable(required = false) String category_name,
                                               @PageableDefault(page = 0,size = 5,sort = "accomNum",direction = Sort.Direction.DESC) Pageable pageable) {

        return accomodationService.getAccomPageList(pageable,category_name);
    }


}
