package com.phoenix.howabouttoday.global;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum RegionType {

    EMPTY("없음",0),
    SEOUL("서울",0),
    GYEONGGI("경기",0),
    INCHEON("인천",0),
    GANGWON("강원",0),
    JEJU("제주",0),
    DAEJEON("대전",0),
    CHUNGCHEONGBUK("충북",0),
    CHUNGNAM_SEJONG("충남/세종",0),
    BUSAN("부산",0),
    ULSAN("울산",0),
    GYEONGSANGNAM("경남",0),
    DAEGU("대구",0),
    GYEONGSANGBUK("경북",0),
    GWANGJU("광주",0),
    JEOLLANAM("전남",0),
    JEOLLABUK("전주/전북",0);


    private String value;
    private Integer code;

    RegionType(String value, Integer code) {
        this.value = value;
        this.code = code;
    }

    public static RegionType fromCode(String dbData){
        return Arrays.stream(RegionType.values())
                .filter(v -> v.getValue().equals(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("지역 카테고리에 %s가 존재하지 않습니다.", dbData)));
    }

}


