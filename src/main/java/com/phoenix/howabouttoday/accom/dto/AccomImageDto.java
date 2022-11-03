package com.phoenix.howabouttoday.accom.dto;


import com.phoenix.howabouttoday.accom.entity.AccomImage;
import lombok.Getter;
import lombok.NoArgsConstructor;



public class AccomImageDto {



    @Getter
    @NoArgsConstructor
    public static class ResponseDto{

        private Long accomNum;


        private String accomOriginFilename;


        private String accomSaveFilename;

        /* Entity -> Dto */

        public ResponseDto(AccomImage accomImage) {
            this.accomNum = accomImage.getAccomNum();
            this.accomOriginFilename = accomImage.getAccomOriginFilename();
            this.accomSaveFilename = accomImage.getAccomSaveFilename();
        }
    }
}
