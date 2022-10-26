//package com.phoenix.howabouttoday.global;
//
//import lombok.Getter;
//
//import java.util.Arrays;
//
//@Getter
//public enum AccomCategory {
//
//    HOTEL("호텔"),
//    MOTEL("모텔"),
//    PENSION("펜션"),
//    GUESTHOUSE("게스트하우스");
//
//    private String value;
//
//    AccomCategory(String value) {
//        this.value = value;
//    }
//
//    public static AccomCategory fromCode(String dbData){
//        return Arrays.stream(AccomCategory.values())
//                .filter(v -> v.getValue().equals(dbData))
//                .findAny()
//                .orElseThrow(() -> new IllegalArgumentException(String.format("숙소 카테고리에 %s가 존재하지 않습니다.", dbData)));
//    }
//}


