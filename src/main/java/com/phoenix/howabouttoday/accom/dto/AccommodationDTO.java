package com.phoenix.howabouttoday.accom.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccommodationDTO {

    private int accomNum;//숙소 번호
    private String accomName;//숙소 이름
    private String accomTel;//숙소 전화번호
    private int accomCategoryNum;//숙소 카테고리 번호
    private int regionNum;//숙소 지역 번호
    private String accomAddress;//숙소 주소
    private Double accomRating;//숙소 평점
    private int accomWishListCount;//찜 갯수
    private int totalreviewNum;//숙소의 총 리뷰 수
    private Double latitude;//위도
    private Double longitude;//경도
    private int lowPrice;//한 숙소의 객실 최저가
    private int reserveRange;//예약 가능일 범위(ex. 60 → 오늘부터 +60일까지 예약 가능)
    private String accomOriginFileName;//숙소 이미지 기존 파일 이름
    private String accomSaveFileName;//숙소 이미지 저장 파일 이름

    @Builder
    public AccommodationDTO(int accomNum,String accomName, String accomTel,int accomCategoryNum,int regionNum,String accomAddress,
                            Double accomRating,int accomWishListCount,int totalreviewNum,Double latitude,Double longitude,int lowPrice,
                            int reserveRange,String accomOriginFileName,String accomSaveFileName) {
        this.accomNum=accomNum;
        this.accomName=accomName;
        this.accomTel=accomTel;
        this.accomCategoryNum=accomCategoryNum;
        this.regionNum=regionNum;
        this.accomAddress=accomAddress;
        this.accomRating=accomRating;
        this.accomWishListCount=accomWishListCount;
        this.totalreviewNum=totalreviewNum;
        this.latitude=latitude;
        this.longitude=longitude;
        this.lowPrice=lowPrice;
        this.reserveRange=reserveRange;
        this.accomOriginFileName=accomOriginFileName;
        this.accomSaveFileName=accomSaveFileName;
    }


}
