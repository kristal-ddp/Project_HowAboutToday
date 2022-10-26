package com.phoenix.howabouttoday.accom.dto;

import com.phoenix.howabouttoday.accom.entity.AccomCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AccomCategoryDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseDto{

        private Long id;
        private String name;

        private String viewName;

        /* Entity -> Dto */
        public ResponseDto(AccomCategory category){
            this.id = category.getAccomCategoryNum();
            this.name = category.getName();
            this.viewName = category.getViewName();
        }
    }
}
