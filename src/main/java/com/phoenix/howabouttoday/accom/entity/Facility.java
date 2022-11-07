package com.phoenix.howabouttoday.accom.entity;

import com.phoenix.howabouttoday.global.RegionType;
import lombok.Getter;

import java.util.Arrays;

/**
 * TV,TWOBED는 예시로 넣어놓음
 */
@Getter
public enum Facility {
    BOUTIQUE_BRAND("부티크 브랜드"),PARKING_OK("주차가능"),OCEAN_VIEW("바다전망"),
    BREAKFAST("조식운영(뷔페)"),NEER_BEACH("해수욕장 인근"),PAID_LAUNDRY("유료세탁"),
    LUGGAGE_STORAGE("수화물보관"),PC_ROUNGE("PC라운지"),FITNESS("피트니스"),
    BUSINESS("비즈니스"),LOOFTOP("루프탑"),SMOKING_ZONE("흡연구역"),RESTAURENT("레스토랑"),
    ALL_TIME_DESK("24시간 데스크"),COFFEE_SHOP("커피숍"),SPA("스파/월풀/욕조"),EAT_OK("식사가능"),
    KARAOKE("노래방"),GAME("게임"),POOL("수영장"),BBQ("바베큐"),STORE("매점/편의점"),
    VILLA("독채객실"),SEASONING("기본양념"),POOL_VILLA("풀빌라");


    private String value;

    Facility(String value) {
        this.value = value;
    }

    public static Facility fromCode(String dbData){
        return Arrays.stream(Facility.values())
                .filter(v -> v.getValue().equals(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("지역 카테고리에 %s가 존재하지 않습니다.", dbData)));
    }
}