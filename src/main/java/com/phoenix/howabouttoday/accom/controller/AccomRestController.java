package com.phoenix.howabouttoday.accom.controller;

import com.phoenix.howabouttoday.accom.dto.AccomDto;

import com.phoenix.howabouttoday.accom.service.AccomodationService;
import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/rest/accom")
@RequiredArgsConstructor
public class AccomRestController {



    private final AccomodationService accomodationService;

    /** 기본 조회 컨트롤러 **/
    @GetMapping("{category_name}")
    public Slice<AccomDto.ResponsePageDto> accommodations(@PathVariable(required = false) String category_name,
                                               @PageableDefault(page = 0,size = 5,sort = "lowPrice",direction = Sort.Direction.ASC) Pageable pageable,
                                                          @RequestParam(value = "keyword",required = false) String keyword,
                                                          @RequestParam(required = false) String priceRange,
                                                          @RequestParam(required = false) String regionNo,
                                                          @RequestParam(required = false) String accomRating) {



        /** 검색어가 없을경우 처리 로직 **/
        if(keyword==null || keyword.equals("") ){
            keyword = "";
        }

        /** 첫랜딩부분에서 가격정렬이 없을때 처리 로직 **/
        if(priceRange==null || keyword.equals("")){
            priceRange = "1만원 - 50만원";
        }

        /** 평점정렬이 null일때 처리 로직 **/
        if(accomRating==null || accomRating.equals("")){
           accomRating = "5";
        }
        /** 파라미터로 온 string을 double로 형변환 **/
        Double rating = Double.parseDouble(accomRating);
        System.out.println("rating = " + rating);


        /** 가격정렬에 대한 처리 로직 **/
        String[] splits = priceRange.split("-");
        priceRange = splits[0] + splits[1];
        String[] priceArray = priceRange.split("만원");
        int minPrice = Integer.parseInt(priceArray[0].strip()) * 10000; //최소가격
        int maxPrice = Integer.parseInt(priceArray[1].strip()) * 10000;  //최대가격


        /** 지역이 있는경우 처리 로직 **/
        if(regionNo!=null){
            Long regionNum = Long.parseLong(regionNo);
           return  accomodationService.getByRegionAccomPageList(regionNum,pageable,category_name,keyword,maxPrice,minPrice,rating);
        }

        return accomodationService.getAccomPageList(pageable,category_name,keyword,maxPrice,minPrice,rating);


    }

    /** 가격 낮은순 컨트롤러 **/
    @GetMapping("/asc/{category_name}")
    public Slice<AccomDto.ResponsePageDto> ascSort(@PathVariable(required = false) String category_name,
                                                          @PageableDefault(page = 0,size = 5,sort = "lowPrice",direction = Sort.Direction.ASC) Pageable pageable,
                                                   @RequestParam(value = "keyword",required = false) String keyword,
                                                   @RequestParam(required = false) String priceRange,
                                                   @RequestParam(required = false) String regionNo,
                                                   @RequestParam(required = false) String accomRating) {

        /** 검색어가 없을경우 처리 로직 **/
        if(keyword==null || keyword.equals("") ){
            keyword = "";
        }

        /** 이름 검색을 했을 경우 지역에대해 초기화하는 로직 **/
        if(regionNo==null){
            regionNo = "null";
        }

        /** 평점정렬이 null일때 처리 로직 **/
        if(accomRating==null || accomRating.equals("")){
            accomRating = "5";
        }
        /** 파라미터로 온 string을 double로 형변환 **/
        Double rating = Double.parseDouble(accomRating);
        System.out.println("rating = " + rating);

        //1만원 - 50만원
        /** 가격정렬에 대한 처리 로직 **/
        String[] splits = priceRange.split("-");
        priceRange = splits[0] + splits[1];
        String[] priceArray = priceRange.split("만원");
        int minPrice = Integer.parseInt(priceArray[0].strip()) * 10000; //최소가격
        int maxPrice = Integer.parseInt(priceArray[1].strip()) * 10000;  //최대가격

        /** 지역이 있는경우 처리 로직 **/
        if(regionNo!="null" && (!regionNo.equals("null"))){
            Long regionNum = Long.parseLong(regionNo);
            return  accomodationService.getByRegionAccomPageList(regionNum,pageable,category_name,keyword,maxPrice,minPrice,rating);
        }


        return accomodationService.getAccomPageList(pageable,category_name,keyword,maxPrice,minPrice,rating);

    }

    /** 가격 높은순 컨트롤러 **/
    @GetMapping("/desc/{category_name}")
    public Slice<AccomDto.ResponsePageDto> descSort(@PathVariable(required = false) String category_name,
                                                   @PageableDefault(page = 0,size = 5,sort = "lowPrice",direction = Sort.Direction.DESC) Pageable pageable,
                                                    @RequestParam(value = "keyword",required = false) String keyword,
                                                    @RequestParam(required = false) String priceRange,
                                                    @RequestParam(required = false) String regionNo,
                                                    @RequestParam(required = false) String accomRating) {

        /** 검색어가 없을경우 처리 로직 **/
        if(keyword==null || keyword.equals("") ){
            keyword = "";
        }
        //1만원 - 50만원
        /** 가격정렬에 대한 처리 로직 **/
        String[] splits = priceRange.split("-");
        priceRange = splits[0] + splits[1];
        String[] priceArray = priceRange.split("만원");
        int minPrice = Integer.parseInt(priceArray[0].strip()) * 10000; //최소가격
        int maxPrice = Integer.parseInt(priceArray[1].strip()) * 10000;  //최대가격

        /** 이름 검색을 했을 경우 지역에대해 초기화하는 로직 **/
        if(regionNo==null){
            regionNo = "null";
        }

        /** 평점정렬이 null일때 처리 로직 **/
        if(accomRating==null || accomRating.equals("")){
            accomRating = "5";
        }
        /** 파라미터로 온 string을 double로 형변환 **/
        Double rating = Double.parseDouble(accomRating);
        System.out.println("rating = " + rating);

        /** 지역이 있는경우 처리 로직 **/
        if(regionNo!="null" && (!regionNo.equals("null"))){
            Long regionNum = Long.parseLong(regionNo);
            return  accomodationService.getByRegionAccomPageList(regionNum,pageable,category_name,keyword,maxPrice,minPrice,rating);
        }

        return accomodationService.getAccomPageList(pageable,category_name,keyword,maxPrice,minPrice,rating);
    }


}
