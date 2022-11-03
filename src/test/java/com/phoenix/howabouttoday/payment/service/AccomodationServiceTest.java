//package com.phoenix.howabouttoday.payment.service;
//
//import com.phoenix.howabouttoday.accom.entity.AccomCategory;
//import com.phoenix.howabouttoday.global.RegionType;
//import com.phoenix.howabouttoday.accom.entity.AccomImage;
//import com.phoenix.howabouttoday.accom.entity.Accommodation;
//import com.phoenix.howabouttoday.accom.entity.Region;
//import com.phoenix.howabouttoday.accom.repository.AccommodationRepository;
//import com.phoenix.howabouttoday.member.entity.Member;
//import com.phoenix.howabouttoday.member.entity.Role;
//import com.phoenix.howabouttoday.member.repository.MemberRepository;
//
//import com.phoenix.howabouttoday.payment.repository.OrdersRepository;
//import com.phoenix.howabouttoday.payment.entity.Orders;
//import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
//import com.phoenix.howabouttoday.reserve.domain.Reservation.ReserveStatus;
//import com.phoenix.howabouttoday.room.entity.Room;
//import com.phoenix.howabouttoday.room.repository.RoomRepository;
//import static org.assertj.core.api.Assertions.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//
//
//@SpringBootTest
//@Transactional
//class AccomodationServiceTest {
//
//    private MemberRepository memberRepository;
//    private AccommodationRepository accommodationRepository;
//    private RoomRepository roomRepository;
//    private OrdersRepository ordersRepository;
//
//    @Autowired
//    public AccomodationServiceTest(MemberRepository memberRepository, AccommodationRepository accommodationRepository, RoomRepository roomRepository, OrdersRepository ordersRepository) {
//        this.memberRepository = memberRepository;
//        this.accommodationRepository = accommodationRepository;
//        this.roomRepository = roomRepository;
//        this.ordersRepository = ordersRepository;
//    }
//
//    private ObjectGenerator objectGenerator = new ObjectGenerator();
//    @Test
//    public void testPaging(){
//
//        for (int i = 0; i < 31; i++) {
//            memberRepository.save(objectGenerator.createMember());
//        }
//
////        List<Member> memberList = memberRepository.findAll();
//
//        PageRequest paging = PageRequest.of(0, 6);
//
//        Page<Member> result = memberRepository.findAll(paging);
//        List<Member> testList = memberRepository.findAll();
//
//        System.out.println("PAGE SIZE" + result.getSize());
//        System.out.println("TOTAL PAGES" + result.getTotalPages());
//        System.out.println("TOTAL COUNT" + result.getTotalElements());
//        System.out.println("NEXT" + result.nextPageable());
//
//        List<Member> list = result.getContent();
//
//        list.forEach(member -> System.out.println(member.getEmail()));
//
//
//    assertThat(testList.get(0).getClass().getName()).isEqualTo(30);
//
//    }
//
//    @Test
//    public void makeMember(){
//        final Integer MEMBER_COUNT = 21;
//
//        for (int i = 0; i < MEMBER_COUNT; i++) {
//            memberRepository.save(objectGenerator.createMember());
//        }
//
//        List<Member> testList = memberRepository.findAll();
//        assertThat(testList.size()).isEqualTo(MEMBER_COUNT);
//    }
//
//    @Test
//    public void makeAccommodation(){
//        final Integer MEMBER_COUNT = 11;
//
//        for (int i = 0; i < MEMBER_COUNT; i++) {
//            memberRepository.save(objectGenerator.createMember());
//        }
//
//        List<Member> testList = memberRepository.findAll();
//        assertThat(testList.size()).isEqualTo(MEMBER_COUNT);
//    }
//
//
//    @Test
//    @Rollback(value = false)
//    public void 생성코드테스트(){
//        Member member = Member.builder().build();
//        memberRepository.save(member);
//        ordersRepository.save(objectGenerator.makeTestData());
//        ordersRepository.save(objectGenerator.makeTestData());
//
//        List<Orders> ordersList = ordersRepository.findAll();
//
//
//        assertThat(ordersList.size()).isEqualTo(2);
//    }
//
//    @Test
//    public void 생성_매핑_테스트() {
//
//        Region region  = Region.builder()
//                .region(RegionType.SEOUL)
//                .regionParentNum(RegionType.SEOUL)
//                .build();
//
//        Accommodation newMember = Accommodation.builder()
//                .accomName("보령(대천) 너울펜션")
//                .accomTel("050350577805")
////                .accomCategory(AccomCategory.PENSION)
//                .region(region)
//                .accomAddress("충청남도 보령시 해수욕장13길 10-20")
//                .accomRating(4.4)
//                .accomWishlistCount(110)
//                .totalReviewNum(1103)
//                .latitude(36.3196)
//                .longitude(126.5092)
//                .lowPrice(45000)
//                .reserveRange(60)
//                .build();
//
//        AccomImage image = AccomImage.builder()
//                .accomOriginFilename("image0.jpg")
//                .accomSaveFilename("image0.jpg")
//                .accommodation(newMember)
//                .build();
//
//        AccomImage image1 = AccomImage.builder()
//                .accomOriginFilename("image0.jpg")
//                .accomSaveFilename("image0.jpg")
//                .accommodation(newMember)
//                .build();
//
//
//        newMember.getAccommodationImage().add(image);
//        newMember.getAccommodationImage().add(image1);
//
//
//        accommodationRepository.save(newMember);
//
//
//        Accommodation test = accommodationRepository.findByAccomNum(newMember.getAccomNum());
//
//        assertThat(test.getAccomName()).isEqualTo(newMember.getAccomName());
//        assertThat(test.getAccommodationImage().size()).isEqualTo(2);
//        assertThat(test.getAccomCategory()).isEqualTo(newMember.getAccomCategory());
//
//        System.out.println(test.getAccomName());
//        System.out.println(test.getAccommodationImage().size());
//        System.out.println(test.getAccomCategory());
//    }
//
//    @Test
//    public void 장바구니에서_결제로_넘기는_테스트(){
//
//        Region region  = Region.builder()
//                .region(RegionType.SEOUL)
//                .regionParentNum(RegionType.SEOUL)
//                .build();
//
//        Member member = Member.builder()
//                .email("ingn@nate.com")
//                .pwd("1111")
//                .nickname("noscarna")
//                .memberTel("01045020614")
//                .role(Role.MEMBER)
//                .joinDate(LocalDate.now())
//                .withdrawdate(LocalDate.now())
//                .memberOriginalFileName("Origin")
//                .memberSaveFileName("save")
//                .build();
//
//
//        Accommodation acco = Accommodation.builder()
//                .accomName("보령(대천) 너울펜션")
//                .accomTel("050350577805")
////                .accomCategory(AccomCategory.PENSION)
//                .region(region)
//                .accomAddress("충청남도 보령시 해수욕장13길 10-20")
//                .accomRating(4.4)
//                .accomWishlistCount(110)
//                .totalReviewNum(1103)
//                .latitude(36.3196)
//                .longitude(126.5092)
//                .lowPrice(45000)
//                .reserveRange(60)
//                .build();
//
//        Room room = Room.builder()
//                .roomName("깨끗한 방")
//                .defaultGuest(2)
//                .maxGuest(4)
//                .price(45000)
//                .roomInfo("고객에게 최선을 다합니다.")
//                .build();
//
//
//        Cart cart = Cart.builder()
//                .member(member)
//                .accommodation(acco)
//                .room(room)
//                .reserveStatus(ReserveStatus.READY)
//                .reserveUseStartDate(LocalDate.now())
//                .reserveUseEndDate(LocalDate.now())
//                .reservePrice(room.getPrice())
//                .reserveAdultCount(room.getDefaultGuest())
//                .build();
//
//        Accommodation acco2 = Accommodation.builder()
//                .accomName("보령(대천) 너울펜션")
//                .accomTel("050350577805")
////                .accomCategory(AccomCategory.PENSION)
//                .region(region)
//                .accomAddress("충청남도 보령시 해수욕장13길 10-20")
//                .accomRating(4.4)
//                .accomWishlistCount(110)
//                .totalReviewNum(1103)
//                .latitude(36.3196)
//                .longitude(126.5092)
//                .lowPrice(45000)
//                .reserveRange(60)
//                .build();
//
//        Room room2 = Room.builder()
//                .roomName("깨끗한 방")
//                .defaultGuest(2)
//                .maxGuest(4)
//                .price(45000)
//                .roomInfo("고객에게 최선을 다합니다.")
//                .build();
//
//
//        Cart cart2 = Cart.builder()
//                .member(member)
//                .accommodation(acco)
//                .room(room)
//                .reserveStatus(ReserveStatus.READY)
//                .reserveUseStartDate(LocalDate.now())
//                .reserveUseEndDate(LocalDate.now())
//                .reservePrice(room.getPrice())
//                .reserveAdultCount(room.getDefaultGuest())
//                .build();
//
//        Orders newOrder = Orders.builder()
//                .ordersTel("01045020614")
//                .ordersName("김영운")
//                .ordersDate(LocalDateTime.now())
//                .ordersPrice(45000+90000)
//                .ordersType("카드")
//                .ordersStatus("결제완료")
//                .build();
//
////        newOrder.getorder().add(cart);
////        newOrder.getReservation().add(cart2);
//
//        System.out.println("하아");
//    }
//
//    @Test
//    public void 객실에서_결제로_넘기는_테스트(){
//
//        Region region  = Region.builder()
//                .region(RegionType.SEOUL)
//                .regionParentNum(RegionType.SEOUL)
//                .build();
//
//
//        Member member = Member.builder()
//                .email("ingn@nate.com")
//                .pwd("1111")
//                .nickname("noscarna")
//                .memberTel("01045020614")
//                .role(Role.MEMBER)
//                .joinDate(LocalDate.now())
//                .withdrawdate(LocalDate.now())
//                .memberOriginalFileName("Origin")
//                .memberSaveFileName("save")
//                .build();
//
//
//        Accommodation acco = Accommodation.builder()
//                .accomName("보령(대천) 너울펜션")
//                .accomTel("050350577805")
////                .accomCategory(AccomCategory.PENSION)
//                .region(region)
//                .accomAddress("충청남도 보령시 해수욕장13길 10-20")
//                .accomRating(4.4)
//                .accomWishlistCount(110)
//                .totalReviewNum(1103)
//                .latitude(36.3196)
//                .longitude(126.5092)
//                .lowPrice(45000)
//                .reserveRange(60)
//                .build();
//
//        Room room = Room.builder()
//                .roomName("깨끗한 방")
//                .defaultGuest(2)
//                .maxGuest(4)
//                .price(45000)
//                .roomInfo("고객에게 최선을 다합니다.")
//                .build();
//
//
//        Cart cart = Cart.builder()
//                .member(member)
//                .accommodation(acco)
//                .room(room)
//                .reserveStatus(ReserveStatus.READY)
//                .reserveUseStartDate(LocalDate.now())
//                .reserveUseEndDate(LocalDate.now())
//                .reservePrice(room.getPrice())
//                .reserveAdultCount(room.getDefaultGuest())
//                .build();
//    }
//
//
//    /** uid로 orders정보 읽기 테스트 **/
//    @Test
//    public void imId(){
//
//        /**회원등록**/
//        Member member = memberRepository.save(Member.builder()
//                .email("bbb@naver.com")
//                .pwd("1234")
//                .memberTel("010-1111-2222")
//                .nickname("안수언")
//                .memberOriginalFileName("Originl")
//                .memberSaveFileName("save1")
//                .joinDate(LocalDate.of(2022,9,27))
//                .role(Role.MEMBER)
//                .build());
//
//
//        Orders order1 = Orders.builder()
//                .ordersTel(member.getMemberTel())
//                .ordersName(member.getNickname())
//                .ordersDate(LocalDateTime.now())
//                .ordersPrice(45555)
//                .ordersType("card")
//                .ordersStatus("결제완료")
//                .merchantId("abc")
//                .member(member)
//                .build();
//
//        /** 예약 등록 **/
//        Orders order2 = Orders.builder()
//                .ordersTel(member.getMemberTel())
//                .ordersName(member.getNickname())
//                .ordersDate(LocalDateTime.now())
//                .ordersPrice(111111)
//                .ordersType("card")
//                .ordersStatus("결제완료")
//                .member(member)
//                .merchantId("def")
//                .build();
//
//        ordersRepository.save(order1);
//        ordersRepository.save(order2);
//
//        Orders repoOrders1 = ordersRepository.findByMerchantId("abc").orElseThrow(() -> new IllegalArgumentException("결제고유정보가 없습니다."));
//        Orders repoOrders2 = ordersRepository.findByMerchantId("def").orElseThrow(() -> new IllegalArgumentException("결제고유정보가 없습니다."));
//
//
//        assertThat(order1.getMerchantId()).isEqualTo(repoOrders1.getMerchantId());
//        assertThat(order2.getMerchantId()).isEqualTo(repoOrders2.getMerchantId());
//
//    }
//}