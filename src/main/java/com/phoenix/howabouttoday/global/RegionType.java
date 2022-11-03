package com.phoenix.howabouttoday.global;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum RegionType {
    /** 시/도 **/
    EMPTY("없음"),
    SEOUL("서울특별시"),
    GYEONGGI("경기도"),
    INCHEON("인천광역시"),
    GANGWON("강원도"),
    JEJU("제주특별자치도"),
    DAEJEON("대전광역시"),
    CHUNGBUK("충청북도"),
    CHUNGNAM("충청남도"),
    BUSAN("부산광역시"),
    ULSAN("울산광역시"),
    GYEONGNAM("경상남도"),
    DAEGU("대구광역시"),
    GYEONGBUK("경상북도"),
    GWANGJU("광주광역시"),
    JEONNAM("전라남도"),
    JEONBUK("전라북도"),
    SEJONG("세종특별자치시"),

    /** 시/군/구  (서울)**/
    KANGNAM("강남구"), KANGDONG("강동구"), KANGBUK("강북구"), KANGSEO("강서구"), GWANAK("관악구"),
    GWANGJIN("광진구"), GURO("구로구"), KEUMCHEON("금천구"), NOWON("노원구"), DOBONG("도봉구"), DONGDEAMOON("동대문구"),
    DONGJAK("동작구"), MAPO("마포구"), SEODEAMOON("서대문구"), SEOCHO("서초구") , SEONGDONG("성동구"), SEONGBOOK("성북구"), SONGPA("송파구"), YANGCHEON("양천구"),
    YEONGDEUNGPO("영등포구"), YONGSAN("용산구"), EUNPYEONG("은평구"), JONGRO("종로구"), JUNG("중구"), JUNGNANG("종랑구"),


    /** 시/군/구 (부산) **/
    GEUMJEONG("금정구"),GIJANG("기장군") ,NAM("남구") , DONG("동구"),
    BUSANJIN("부산진구"), BUK("북구") , DONGNAE("동래구"), SASANG("사상구") ,
    SAHA("사하구") , SEO("서구") , SUYEONG("수영구"), YEONJE("연제구") ,
    YEONGDO("영도구") , HAEUNDAE("해운대구"),

    /** 시/군/구 (대구) **/
    DALSEO("달서구") , DALSEONG("달성군") , SUSEONG("수성구"),

    /** 시/군/구 (인천) **/
    GANGHWA("강화군") , GYEYANG("계양구") , NAMDONG("남동구") , MICHUHOL("미추홀구"),
    BUPYEOnG("부평구") , YEONSU("연수구") , ONGJIN("옹진군"),

    /**  시/군/구 (광주) **/
    GWANGSAN("광산구"),

    /** 시/군/구 (대전) **/
    DAEDEOK("대덕구") , YOOSEONG("유성구"),

    /** 시/군/구 (울산) **/
    ULJU("울주군"),

    /** 시/군/구 (경기) **/
    GAPYEONG("가평군"),GOYANG_DEOKYANG("고양시 덕양구"), GOYANG_SEO("고양시 일산서구"),
    GOYANG_DONG("고양시 일산동구"), GWACHEON("과천시"),
    GWANGMYEONG("광명시"), KYEONGKI_GWANGJU("광주시"), GURI("구리시"), GUNPO("군포시") ,
    KIMPO("김포시"), NAMYANGJU("남양주시"), DONGDOOCHEON("동두천시"), BUCHEON("부천시") ,
    SEONGNAM_BUNDANG("성남시 분당구"),SEONGNAM_SUJENG("성남시 수정구"), SEONGNAM_JUNGWON("성남시 중원구"),
    SUWON_GWONSEON("수원시 권선구"), SUWON_YEONGTONG("수원시 영통구"), SUWON_JANGAN("수원시 장안구"),
    SUWON_PALDAL("수원시 팔달구"), SIHEUNG("시흥시"), ANSAN_DANWON("안산시 단원구"),
    ANSAN_SANGNOK("안산시 상록구"), ANSEONG("안성시"),
    ANYANG_DONGAN("안양시 동안구"), ANYANG_MANAN("안양시 만안구"), YANGJU("양주시"), YANGPYEONG("양평군"),
    YEOJU("여주시"), YEONCHEON("연천군"), OSAN("오산시"), YONGIN_GIHEUNG("용인시 기흥구"),
    YONGIN_SUZI("용인시 수지구"), YONGIN_CHEOIN("용인시 처인구"), UIWANG("의왕시"), UIJEONGBU("의정부시"),
    ICHEON("이천시"), PAJU("파주시"), PYEONGTAEK("평택시"), POCHEON("포천시"),HANAM("하남시"),
    HWASEONG("화성시"),

    /** 시/군/구 (강원) **/
    KANGNEUNG("강릉시"), GOSUNG("고성군") , DONGHEA("동해시") , SAMCHEOK("삼척시") ,
    SOKCHO("속초시"), YANGGU("양구군"), YANGYANG("양양군"), YEONGWOL("영월군"),
    WONJU("원주시"), INJE("인제군"), JEONGSUN("정선군") , CHEOLWON("철원군"),
    CHUNCHEON("춘천시"), TAEBAEK("태백시"), PYEONGCHANG("평창군"), HONGCHEON("홍천군"),
    HWACHEON("화천군"), HOENGSEONG("횡성군"),

    /** 시/군/구 (충북) **/
    GOESAN("괴산군"),DANYANG("단양군"),BOEUN("보은군"),YEONGDONG("영동군"),OKCHEON("옥천군"),
    EUMSEONG("음성군"),JECHEON("제천시"),JEUNGPYEONG("증평군"),JINCHEON("진천군"),
    CHEONGJU_SANGDANG("청주시 상당구"), CHEONGJU_SEOWON("청주시 서원구"),CHEONGJU_CHEONGWON("청주시 청원구"),
    CHEONGJU_HEUNGDEOK("청주시 흥덕구"),CHUNGJU("충주시"),

    /** 시/군/구 (충남) **/
    GYERYONG("계룡시") , GONGJU("공주시") , GEUMSAN("금산군") , NONSAN("논산시") ,
    DANGJIN("당진시") , BORYEONG("보령시") , BUYEO("부여군"),
    SEOSAN("서산시") , SEOCHEON("서천군") , ASAN("아산시") , YESAN("예산군") ,
    CHEONAN_DONGNAM("천안시 동남구") , CHEONAN_SEOBUK("천안시 서북구"),
    CHEONGYANG("청양군"), TAEAN("태안군") , HONGSEONG("홍성군"),


    /** 시/군/구 (전북) **/
    GOCHANG("고창군"), GUNSAN("군산시") , GIMJE("김제시") , NAMWON("남원시") ,
    BUAN("부안군"), MUJU("무주군") , SUNCHANG("") , WANJU("완주군") , IKSAN("익산시") ,
    IMSIL("임실군"), JANGSU("장수군") , JEONJU_DEOKJIN("전주시 덕진구"), JEONJU_WANSAN("전주시 완산구"), JEONGEUP("") ,
    JINAN("진안군"),

    /** 시/군/구 (전남) **/
    GANGJIN("강진군"), GOHEUNG("고흥군") , GOKSEONG("곡성군") , GWANGYANG("광양시") ,
    GURYE("구례군") , NAJU("나주시") , DAMYANG("담양군"),
    MOKPO("목포시") , MUAN("무안군") , BOSEONG("보성군") , SUNCHEON("순천시") ,
    SINAN("신안군") , YEOSU("여수시") , YEONGWANG("영광군") , YEONGAM("영암군"),
    WANDO("완도군") , JANGSEONG("장성군") , JANGHEUNG("장흥군") , JINDO("진도군"),
    HAMPYEONG("함평군"), HAENAM("해남군") , HWASUN("화순군"),

    /** 시/군/구 (경북) **/
    GYEONGSAN("경산시") , GYEONGJU("경주시"), GORYEONG("고령군"), GUMI("구미시") ,
    GUNWI("군위군") , GIMCHEON("김천시") ,
    MUNGYEONG("문경시") , BONGHWA("봉화군") , SANGJU("상주시") ,
    SEONGJU("성주군") , ANDONG("안동시") , YEONGDEOK("영덕군"),
    YEONGYANG("영양군"), YEONGJU("영주시") , YEONGCHEON("영천시"),
    YECHEON("예천군") , ULLEUNG("울릉군"), ULJIN("울진군") , UISEONG("의성군"),
    CHEONGDO("청도군") , CHEONGSONG("청송군") , CHILGOK("칠곡군") , POHANG_NAM("포항시 남구"),
    POHANG_BUK("포항시 북구"),


    /** 시/군/구 (경남) **/
    GEOJE("거제시") , GEOCHANG("거창군") , GOSEONG("고성군") , GIMHAE("김해시") ,
    NAMHAE("남해군") , MIRYANG("밀양시"),
    SACHEON("사천시") , SANCHEONG("산천궁") , YANGSAN("양산시") , UIRYEONG("의령군") ,
    JINJU("진주시") , CHANGNYEONG("창녕군") ,
    MASAN_HAPPO("창원시 마산합포구") , MASAN_HUIWON("창원시 마산회원구") ,
    CHANGWON_SEONGSAN("창원시 성산구"),
    CHANGWON_UICHANG("창원시 의창구"), CHANGWON_JINHAE("창원시 진해구"),HAMAN("함안군"),
    TONGYEONG("통영시"), HADONG("하동군"), HAMYANG("함양군"), HAPCHEON("합천군") ,

    /** 시/군/구 (제주) **/
    SEOGWIPO("서귀포시"), JEJU_JEJU("제주시");




    private String value;


    RegionType(String value) {
        this.value = value;
    }

    public static RegionType fromCode(String dbData){
        return Arrays.stream(RegionType.values())
                .filter(v -> v.getValue().equals(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("지역 카테고리에 %s가 존재하지 않습니다.", dbData)));
    }

}


