package com.phoenix.howabouttoday.accom.entity;

/**
 * TV,TWOBED는 예시로 넣어놓음
 */

public enum Facility {
    WIFI("와이파이"),SWIMMING_POOL("수영장"),TELEVISION("삼성 40인치 TV"),COFFEE("커피"),AIR_CONDITIONING("공기청정기"),FITNESS_FACILLITY("피트니스-시설"),
    FRIDGE("냉장고"),WINE_BAR("와인바"),ENTERTAINMENT("오락시설"),SECUREVAULT("보안 금고"),FREEPARKING("무료 주차"),
    ELEVATOR_IN_BUILDING("엘리베이터 있음"),HANDICAP_ACCESSIBLE("장애인 이용 가능"),FIRE_PLACE("난방 가능"),PETS_ALLOWED("애완 동물 허용");



    private String krName;

    Facility(String krName) {
        this.krName = krName;
    }

    public String getKrName() {
        return krName;
    }
}
