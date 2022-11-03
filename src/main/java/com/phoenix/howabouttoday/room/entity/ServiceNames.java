package com.phoenix.howabouttoday.room.entity;

public enum ServiceNames {

    BICYCLEHIRE("자전거 대여"),CONFERENCE_ROOMS("회의실"),FRUIT_BASKET("과일 바구니"),MASSAGE("마사지"),SIGHTSEEING("관광")
    ,CARHIRE("렌터카"),FITNESSCENTER("피트니스 센터"),LAUNDRY("빨래"),OWN_PARKING_SPACE("자체 주차 공간"),WAKE_UP_CALL("모닝콜"),;


    private String krName;

    ServiceNames(String krName) {
        this.krName = krName;
    }

    public String getKrName() {
        return krName;
    }

}
