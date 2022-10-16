package com.phoenix.howabouttoday.payment.service;

import com.phoenix.howabouttoday.accom.RegionType;
import com.phoenix.howabouttoday.accom.entity.AccomImage;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.entity.Region;
import com.phoenix.howabouttoday.accom.repository.AccommodationRepository;
import com.phoenix.howabouttoday.member.entity.Code;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import com.phoenix.howabouttoday.payment.AccomCategory;
import com.phoenix.howabouttoday.payment.Orders;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.reserve.domain.Reservation.ReserveStatus;
import com.phoenix.howabouttoday.room.entity.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
//@Transactional
class AccomodationServiceTest {

    @Autowired
    private AccommodationRepository accommodationRepository;
//    private final AccommodationImageRepository accommodationImageRepository;

    @Autowired
    private MemberRepository memberRepository;
    private ObjectGenerator objectGenerator = new ObjectGenerator();
    @Test
    public void testPaging(){

        for (int i = 0; i < 31; i++) {
            memberRepository.save(objectGenerator.createMember());
        }

//        List<Member> memberList = memberRepository.findAll();

        PageRequest paging = PageRequest.of(0, 6);

        Page<Member> result = memberRepository.findAll(paging);
        List<Member> testList = memberRepository.findAll();

        System.out.println("PAGE SIZE" + result.getSize());
        System.out.println("TOTAL PAGES" + result.getTotalPages());
        System.out.println("TOTAL COUNT" + result.getTotalElements());
        System.out.println("NEXT" + result.nextPageable());

        List<Member> list = result.getContent();

        list.forEach(member -> System.out.println(member.getEmail()));


//        Assertions.assertThat(testList.get(0).getClass().getName()).isEqualTo(30);

    }

    @Test
    public void 생성_매핑_테스트() {

        Region region  = Region.builder()
                .region(RegionType.SEOUL)
                .regionParentNum(RegionType.SEOUL)
                .build();

        Accommodation newMember = Accommodation.builder()
                .accomName("보령(대천) 너울펜션")
                .accomTel("050350577805")
                .accomCategory(AccomCategory.PENSION)
                .region(region)
                .accomAddress("충청남도 보령시 해수욕장13길 10-20")
                .accomRating(4.4)
                .accomWishlistCount(110)
                .totalReviewNum(1103)
                .latitude(36.3196)
                .longitude(126.5092)
                .lowPrice(45000)
                .reserveRange(60)
                .build();

        AccomImage image = AccomImage.builder()
                .accomOriginFilename("image0.jpg")
                .accomSaveFilename("image0.jpg")
                .accommodation(newMember)
                .build();

        AccomImage image1 = AccomImage.builder()
                .accomOriginFilename("image0.jpg")
                .accomSaveFilename("image0.jpg")
                .accommodation(newMember)
                .build();


        newMember.getAccommodationImage().add(image);
        newMember.getAccommodationImage().add(image1);


        accommodationRepository.save(newMember);


        Accommodation test = accommodationRepository.findByAccomNum(newMember.getAccomNum());

        Assertions.assertThat(test.getAccomName()).isEqualTo(newMember.getAccomName());
        Assertions.assertThat(test.getAccommodationImage().size()).isEqualTo(2);
        Assertions.assertThat(test.getAccomCategory()).isEqualTo(newMember.getAccomCategory());

        System.out.println(test.getAccomName());
        System.out.println(test.getAccommodationImage().size());
        System.out.println(test.getAccomCategory());
    }

    @Test
    public void 장바구니에서_결제로_넘기는_테스트(){

        Region region  = Region.builder()
                .region(RegionType.SEOUL)
                .regionParentNum(RegionType.SEOUL)
                .build();

        Member member = Member.builder()
                .email("ingn@nate.com")
                .pwd("1111")
                .nickname("noscarna")
                .memberTel("01045020614")
                .memberCode(Code.MEMBER)
                .joinDate(LocalDateTime.now())
                .withdrawdate(LocalDateTime.now())
                .memberOriginalFileName("Origin")
                .memberSaveFileName("save")
                .build();


        Accommodation acco = Accommodation.builder()
                .accomName("보령(대천) 너울펜션")
                .accomTel("050350577805")
                .accomCategory(AccomCategory.PENSION)
                .region(region)
                .accomAddress("충청남도 보령시 해수욕장13길 10-20")
                .accomRating(4.4)
                .accomWishlistCount(110)
                .totalReviewNum(1103)
                .latitude(36.3196)
                .longitude(126.5092)
                .lowPrice(45000)
                .reserveRange(60)
                .build();

        Room room = Room.builder()
                .roomName("깨끗한 방")
                .defaultGuest(2)
                .maxGuest(4)
                .price(45000)
                .roomInfo("고객에게 최선을 다합니다.")
                .build();


        Cart cart = Cart.builder()
                .member(member)
                .accommodation(acco)
                .room(room)
                .reserveStatus(ReserveStatus.READY)
                .reserveUseStartDate(LocalDate.now())
                .reserveUseEndDate(LocalDate.now())
                .reservePrice(room.getPrice())
                .reserveAdultCount(room.getDefaultGuest())
                .build();

        Accommodation acco2 = Accommodation.builder()
                .accomName("보령(대천) 너울펜션")
                .accomTel("050350577805")
                .accomCategory(AccomCategory.PENSION)
                .region(region)
                .accomAddress("충청남도 보령시 해수욕장13길 10-20")
                .accomRating(4.4)
                .accomWishlistCount(110)
                .totalReviewNum(1103)
                .latitude(36.3196)
                .longitude(126.5092)
                .lowPrice(45000)
                .reserveRange(60)
                .build();

        Room room2 = Room.builder()
                .roomName("깨끗한 방")
                .defaultGuest(2)
                .maxGuest(4)
                .price(45000)
                .roomInfo("고객에게 최선을 다합니다.")
                .build();


        Cart cart2 = Cart.builder()
                .member(member)
                .accommodation(acco)
                .room(room)
                .reserveStatus(ReserveStatus.READY)
                .reserveUseStartDate(LocalDate.now())
                .reserveUseEndDate(LocalDate.now())
                .reservePrice(room.getPrice())
                .reserveAdultCount(room.getDefaultGuest())
                .build();

        Orders newOrder = Orders.builder()
                .ordersTel("01045020614")
                .ordersName("김영운")
                .ordersDate("2022-10-13")
                .ordersPrice(45000+90000)
                .ordersType("카드")
                .ordersStatus("이용 전")
                .build();

        newOrder.getReservation().add(cart);
        newOrder.getReservation().add(cart2);

        System.out.println("하아");
    }

    @Test
    public void 객실에서_결제로_넘기는_테스트(){

        Region region  = Region.builder()
                .region(RegionType.SEOUL)
                .regionParentNum(RegionType.SEOUL)
                .build();


        Member member = Member.builder()
                .email("ingn@nate.com")
                .pwd("1111")
                .nickname("noscarna")
                .memberTel("01045020614")
                .memberCode(Code.MEMBER)
                .joinDate(LocalDateTime.now())
                .withdrawdate(LocalDateTime.now())
                .memberOriginalFileName("Origin")
                .memberSaveFileName("save")
                .build();


        Accommodation acco = Accommodation.builder()
                .accomName("보령(대천) 너울펜션")
                .accomTel("050350577805")
                .accomCategory(AccomCategory.PENSION)
                .region(region)
                .accomAddress("충청남도 보령시 해수욕장13길 10-20")
                .accomRating(4.4)
                .accomWishlistCount(110)
                .totalReviewNum(1103)
                .latitude(36.3196)
                .longitude(126.5092)
                .lowPrice(45000)
                .reserveRange(60)
                .build();

        Room room = Room.builder()
                .roomName("깨끗한 방")
                .defaultGuest(2)
                .maxGuest(4)
                .price(45000)
                .roomInfo("고객에게 최선을 다합니다.")
                .build();


        Cart cart = Cart.builder()
                .member(member)
                .accommodation(acco)
                .room(room)
                .reserveStatus(ReserveStatus.READY)
                .reserveUseStartDate(LocalDate.now())
                .reserveUseEndDate(LocalDate.now())
                .reservePrice(room.getPrice())
                .reserveAdultCount(room.getDefaultGuest())
                .build();
    }
}