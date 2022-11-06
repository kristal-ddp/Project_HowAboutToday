package com.phoenix.howabouttoday.room.entity;

public enum AmenitiesNames {

    FREE_MOVIE("무료영화(OTT)"),PARTY_ROOM("파티룸"),WI_FI("와이파이"),PARTY_OK("파티가능"),
    ROOM_NO_SMOKING("객실금연"),COMMON_KITCHEN("공용주방"),COMMON_LIVING_ROOM("공용거실"),
    DORMITORY("도미토리"),COUPLE_ROOM("커플룸"),TWIN_BED("트윈베드"),OUTDOOR_POOL("야외수영장"),
    ROOM_PC("객실내PC"),VOD("VOD"),HIGH_END_PC("고사양PC"),AIR_CLEANER("공기청정기"),
    OUTDOOR_TERRACE("야외테라스"),MIRROR_ROOM("거울룸"),COUPLE_PC("커플PC"),INDIVIDUAL_BBQ("개별바베큐"),
    DUPLEX_ROOM("복층구조"),EVENT_OK("이벤트가능");


    private String krName;

    AmenitiesNames(String krName) {
        this.krName = krName;
    }

    public String getKrName() {
        return krName;
    }
}