package com.phoenix.howabouttoday.room.entity;

public enum AmenitiesNames {

    TWO_SOFA("2인용 소파"),SAMSUNG_LED_TV("40인치 삼성 LED TV"),FREE_WI_FI("무료 와이파이"),PRIVATE_POOL("프라이빗 풀"),
    ROOM_SERVICE("24시간 룸서비스"),AIR_CONDITIONING("공기 청정기"),DIRECT_DIAL_PHONE("직통 전화"),
    HAIR_DRYER("헤어 드라이어"),BATHTUB("스파/월풀/욕조"),SAFE_DEPOSIT_BOX("안전 금고"),LUGGAGE_STORAGE("수하물 보관소"),TWIN_BED("트윈베드");


    private String krName;

    AmenitiesNames(String krName) {
        this.krName = krName;
    }

    public String getKrName() {
        return krName;
    }
}