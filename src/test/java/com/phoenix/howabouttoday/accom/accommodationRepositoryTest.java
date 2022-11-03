//package com.phoenix.howabouttoday.accom;
//
//
//import com.phoenix.howabouttoday.accom.dto.AccommodationDTO;
//import com.phoenix.howabouttoday.accom.entity.Accommodation;
//import com.phoenix.howabouttoday.accom.entity.Region;
//import com.phoenix.howabouttoday.accom.repository.AccommodationRepository;
//import com.phoenix.howabouttoday.accom.repository.RegionRepository;
//import com.phoenix.howabouttoday.global.RegionType;
//import com.phoenix.howabouttoday.member.entity.Member;
//import com.phoenix.howabouttoday.member.repository.MemberRepository;
//import groovy.util.logging.Slf4j;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.test.annotation.Rollback;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///*
//
//T_ACCOMMODATION	숙소테이블
//accomNum	숙소번호
//accomName	숙소이름
//accomTel	숙소전화번호
//accomCategoryNum	카테고리번호
//regionNum	지역번호
//accomAddress	숙소주소
//accomRating	숙소평점
//accomWishListCount	찜갯수
//totalreviewNum	총리뷰갯수
//latitude	위도
//longitude	경도
//lowprice	최저가
//reserveRange	예약가능범위
// */
//
//@Transactional
//@SpringBootTest
//@Rollback(false)
//@Slf4j
//public class accommodationRepositoryTest {
//
//    @Autowired
//    AccommodationRepository accommodationRepository;
//
//    @Autowired
//    RegionRepository regionRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    public void AccommodationList() {
//
//        String accomName = "테스트 이름";
//        String accomAddress = "테스트 주소";
//
//
//        accommodationRepository.save(Accommodation.builder()
//                        .accomName(accomName)
//                        .accomAddress(accomAddress)
//                        .build());
//
//        List<Accommodation> accommodationList = accommodationRepository.findAll();
//
//        assertThat(accommodationList.get(0).getAccomName()).isEqualTo(accomName);
//    }
//    @Test
//    public void 지역_추가() {
//
//        Region save = regionRepository.save(Region.builder()
//                .region(RegionType.SEOUL)
//                .regionParentNum(RegionType.SEOUL)
//                .build());
//
//        assertThat(save.getRegionParentNum()).isEqualTo(RegionType.SEOUL);
//
//
//    }
//
//    /*@Test
//    public void 검색() {
//
//        //PageRequest pageRequest = new PageRequest(0,10, new Sort(Sort.Direction.DESC,"name");
//
//
//        //List<Accommodation> search = AccommodationRepository.findByAccomNameLike("보");
//        List<Accommodation> orderPrice = accommodationRepository.findAllByOrderByLowPrice();
//        for (Accommodation accommodation : orderPrice) {
//            System.out.println("accommodation.getLowPrice() = " + accommodation.getLowPrice());
//        }
//
//    }
//
//    @Test
//    public void 정렬역순() {
//
//        //PageRequest pageRequest = new PageRequest(0,10, new Sort(Sort.Direction.DESC,"name");
//
//
//        //List<Accommodation> search = AccommodationRepository.findByAccomNameLike("보");
//        List<Accommodation> orderPrice = accommodationRepository.findAllByOrderByLowPriceDesc();
//        for (Accommodation accommodation : orderPrice) {
//            System.out.println("accommodation.getLowPrice() = " + accommodation.getLowPrice());
//        }
//
//    }*/
//
//}
