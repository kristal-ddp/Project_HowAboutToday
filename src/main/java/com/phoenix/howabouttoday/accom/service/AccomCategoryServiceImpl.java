package com.phoenix.howabouttoday.accom.service;

import com.phoenix.howabouttoday.accom.dto.AccomCategoryDto;
import com.phoenix.howabouttoday.accom.entity.AccomCategory;
import com.phoenix.howabouttoday.accom.repository.AccomCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class    AccomCategoryServiceImpl implements AccomCategoryService{

    private final AccomCategoryRepository accomCategoryRepository;

    @Override
    public List<AccomCategoryDto.ResponseDto> findAccomList() {
        List<AccomCategory> categoryList = accomCategoryRepository.findAll();

        /* 반환한 Category List 컬렉션 객체를 CategoryDto.ResponseDto List 컬렉션 객체로 변환 */
        return categoryList.stream().map(AccomCategoryDto.ResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public String getAccomViewName(String name) {
        AccomCategory category = accomCategoryRepository.findByName(name);
        return category.getViewName();
    }


}
