package com.phoenix.howabouttoday.accom.service;

import com.phoenix.howabouttoday.accom.dto.AccomCategoryDto;

import java.util.List;

public interface AccomCategoryService {

    List<AccomCategoryDto.ResponseDto> findAccomList();

    String getAccomViewName(String name);


}
