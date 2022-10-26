package com.phoenix.howabouttoday;


import com.phoenix.howabouttoday.accom.entity.*;
import com.phoenix.howabouttoday.accom.repository.*;
import com.phoenix.howabouttoday.board.entity.Reply;
import com.phoenix.howabouttoday.board.entity.Review;
import com.phoenix.howabouttoday.board.entity.ReviewImage;
import com.phoenix.howabouttoday.board.repository.*;

import com.phoenix.howabouttoday.global.RegionType;
import com.phoenix.howabouttoday.member.entity.Role;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;

import com.phoenix.howabouttoday.member.wishlist.domain.WishList;
import com.phoenix.howabouttoday.member.wishlist.domain.WishlistRepository;
import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.payment.entity.OrdersDetail;

import com.phoenix.howabouttoday.payment.repository.OrdersRepository;


import com.phoenix.howabouttoday.reserve.domain.CartRepository;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Reservation;
import com.phoenix.howabouttoday.reserve.domain.Reservation.ReserveStatus;
import com.phoenix.howabouttoday.room.entity.AvailableDate;
import com.phoenix.howabouttoday.room.entity.*;
import com.phoenix.howabouttoday.room.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();

    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        private final MemberRepository memberRepository;
        private final RegionRepository regionRepository;
        private final CartRepository cartRepository;
        private final AccommodationRepository accommodationRepository;
        private final FacilitiesRepository facilitiesRepository;
        private final AccomViewFaciltiesRepository accomViewFaciltiesRepositoryory;
        private final BoardCategoryRepository boardCategoryRepository;
        private final BoardRepository boardRepository;
        private final EventImageRepository eventImageRepository;
        private final EventRepository eventRepository;
        private final RoomImageRepository roomImageRepository;
        private final RoomRepository roomRepository;
        private final ReviewRepository reviewRepository;
        private final ReviewImageRepository reviewImageRepository;
        private final ReplyRepository replyRepository;
        private final AmenitiesRepository amenitiesRepository;
        private final ServiceRepository serviceRepository;
        private final WishlistRepository wishlistRepository;
        private final OrdersRepository ordersRepository;
//        private final OrdersDetailRepository ordersDetailRepository;
        private final AccommodationImageRepository accommodationImageRepository;
        private final RoomViewAmenitiesRepository roomViewAmenitiesRepository;

        private final AccomCategoryRepository accomCategoryRepository;
        public void dbInit1() {

            /**회원등록**/
            Member member = memberRepository.save(Member.builder()
                    .email("aaa@naver.com")
                    .pwd("123")
                    .memberTel("010-1234-5678")
                    .nickname("이동우")
                    .memberOriginalFileName("Originl")
                    .memberSaveFileName("save")
                    .joinDate(LocalDate.of(2022,10,27))
                    .role(Role.MEMBER)
                    .build());


            /**지역 등록 **/
            Region region = regionRepository.save(Region.builder()
                    .region(RegionType.SEOUL)
                    .regionParentNum(RegionType.SEOUL)
                    .build());

            /** 카테고리 등록 **/

            AccomCategory hotel = accomCategoryRepository.save(AccomCategory.builder()
                    .name("hotel")
                    .viewName("호텔")
                    .build());




            /**숙소 등록**/
            Accommodation accommodation = accommodationRepository.save(Accommodation.builder()
                    .accomName("보령(대천) 너울펜션")
                    .accomTel("050350577805")
                    .accomCategory(hotel)
                    .region(region)
                    .accomAddress("충청남도 보령시 해수욕장13길 10-20")
                    .accomRating(4.4)
                    .accomWishlistCount(110)
                    .totalReviewNum(1103)
                    .latitude(36.3196)
                    .longitude(126.5092)
                    .checkIn(LocalTime.of(15, 0))
                    .checkOut(LocalTime.of(11, 0))
                    .lowPrice(45000)
                    .reserveRange(60)
                    .build());

            /** 숙소시설 등록 **/
            facilitiesRepository.save(Facilities.builder()
                    .facility(Facility.TV)
                    .faciltiesOriginalFileName("image5.jpg")
                    .faciltiesSaveFilename("image7.jpg")
                    .build());

            /** 숙소이미지 등록 **/
            accommodationImageRepository.save( AccomImage.builder()
                    .accomOriginFilename("image0.jpg")
                    .accomSaveFilename("image0.jpg")
                    .accommodation(accommodation)
                    .build());


            /** 객실등록 **/
            Room room = roomRepository.save(Room.builder()
                    .accommodation(accommodation)
                    .roomName("너울펜션 스위트룸")
                    .defaultGuest(2)
                    .maxGuest(10)
                    .price(50)
                    .roomInfo("임시 객실정보 입니다")
                    .build());

            Room room1 = roomRepository.save(Room.builder()
                    .accommodation(accommodation)
                    .roomName("너울펜션 디럭스룸")
                    .defaultGuest(2)
                    .maxGuest(10)
                    .price(70)
                    .roomInfo("임시 객실정보 입니다")
                    .build());

            Room room4 = roomRepository.save(Room.builder()
                    .accommodation(accommodation)
                    .roomName("너울펜션 기가막힌 룸")
                    .defaultGuest(2)
                    .maxGuest(10)
                    .price(80000)
                    .roomInfo("임시 객실정보 입니다")
                    .build());


            /** 객실 이미지 등록 **/
            roomImageRepository.save(RoomImage.builder()
                    .roomOriginFileName("image0.jpg")
                    .roomSaveFileName("image0.jpg")
                    .room(room)
                    .build());

            roomImageRepository.save(RoomImage.builder()
                    .roomOriginFileName("image0.jpg")
                    .roomSaveFileName("image0.jpg")
                    .room(room1)
                    .build());

            roomImageRepository.save(RoomImage.builder()
                    .roomOriginFileName("image0.jpg")
                    .roomSaveFileName("image0.jpg")
                    .room(room4)
                    .build());


            /** 객실 오락시설 등록 **/
            Amenities amenities = amenitiesRepository.save(Amenities.builder()
                    .amenitiesName("흡연장")
                    .build());

            Amenities amenities1 = amenitiesRepository.save(Amenities.builder()
                    .amenitiesName("풋살장")
                    .build());

            /** 객실 서비스 등록 **/
            serviceRepository.save(Service.builder()
                    .serviceName("피트니스")
                    .build());

            /** 객실과 오락시설 등록 **/
            roomViewAmenitiesRepository.save(RoomViewAmenities.builder()
                    .room(room)
                    .amenities(amenities)
                    .build());

            roomViewAmenitiesRepository.save(RoomViewAmenities.builder()
                    .room(room)
                    .amenities(amenities1)
                    .build());

            /**위시리스트 등록**/
//            wishlistRepository.save(WishList.builder()
//                    .member(member)
//                    .accommodation(accommodation)
//                    .build());

            /** 장바구니 등록 **/
            Cart cart = cartRepository.save(Cart.builder()
                    .member(member)
                    .room(room)
                    .reserveUseStartDate(LocalDate.of(2022, 10, 18))
                    .reserveUseEndDate(LocalDate.of(2022, 10, 20))
                    .reservePrice(room.getPrice())
                    .reserveAdultCount(2)
                    .reserveChildCount(1)
                    .build());

            Cart cart1 = cartRepository.save(Cart.builder()
                    .member(member)
                    .room(room1)
                    .reserveUseStartDate(LocalDate.of(2022, 10, 23))
                    .reserveUseEndDate(LocalDate.of(2022, 10, 26))
                    .reservePrice(room1.getPrice())
                    .reserveAdultCount(4)
                    .reserveChildCount(1)
                    .build());

            /** 예약 등록 **/
            Orders order = Orders.builder()
                    .ordersTel(member.getMemberTel())
                    .ordersName(member.getNickname())
                    .ordersDate(LocalDate.now())
                    .ordersPrice(room.getPrice())
                    .ordersType("card")
                    .ordersStatus("결제완료")
                    .impUid("abc")
                    .member(member)
                    .build();

            OrdersDetail ordersDetail = OrdersDetail.builder()
                    .member(cart.getMember())
                    .accommodation(cart.getAccommodation())
                    .room(cart.getRoom())
                    .orders(order)
                    .reserveStatus(ReserveStatus.READY)
                    .reserveUseStartDate(cart.getReserveUseStartDate())
                    .reserveUseEndDate(cart.getReserveUseEndDate())
                    .reservePrice(cart.getReservePrice())
                    .reserveAdultCount(cart.getReserveAdultCount())
                    .reserveChildCount(cart.getReserveChildCount())
                    .build();

            order.getReservation().add(ordersDetail);
            member.getOrders().add(order);

            //ordersDetailRepository.save(ordersDetail);
            ordersRepository.save(order);


            객실예약정보_입력(member.getMemberNum());

            /** 주문 등록 **/


            /** 댓글 등록 **/
            Review review = reviewRepository.save(Review.builder()
                    .member(member)
                    .reviewCreatedDate(LocalDateTime.now())
                    .reviewModifyDate(LocalDateTime.now())
                    .reviewRating(3.72)
                    .reviewContent("안녕")
                    .build());


            /** 댓글 이미지 등록 **/
            reviewImageRepository.save(ReviewImage.builder()
                    .review(review)
                    .reviewOriginalFileName("Original")
                    .reviewSaveFileName("Svae")
                    .build());

            /** 리플 **/
            replyRepository.save(Reply.builder()
                    .member(member)
                    .review(review)
                    .content("이용해 주셔서 감사합니다")
                    .replyCreatedDate(LocalDateTime.now())
                    .replyModifyDate(LocalDateTime.now())
                    .build());


            /** 매핑테이블들 **/


        }

        public void dbInit2() {


            /**회원등록**/
            Member member = memberRepository.save(Member.builder()
                    .email("bbb@naver.com")
                    .pwd("1234")
                    .memberTel("010-1111-2222")
                    .nickname("안수언")
                    .memberOriginalFileName("Originl")
                    .memberSaveFileName("save1")
                    .joinDate(LocalDate.of(2022,9,27))
                    .role(Role.MEMBER)
                    .build());

            /**지역 등록 **/
            Region region = regionRepository.save(Region.builder()
                    .region(RegionType.BUSAN)
                    .regionParentNum(RegionType.BUSAN)
                    .build());

            AccomCategory motel = accomCategoryRepository.save(AccomCategory.builder()
                    .name("motel")
                    .viewName("모텔")
                    .build());

            AccomCategory penssion = accomCategoryRepository.save(AccomCategory.builder()
                    .name("penssion")
                    .viewName("펜션/풀빌라")
                    .build());


            AccomCategory guestHouse = accomCategoryRepository.save(AccomCategory.builder()
                    .name("guesthouse")
                    .viewName("게스트하우스")
                    .build());

            /**숙소 등록**/
            Accommodation accommodation = accommodationRepository.save(Accommodation.builder()
                    .accomName("서울 아폴로 게스트하우스")
                    .accomTel("050350521568")
                    .accomCategory(penssion)
                    .region(region)
                    .accomAddress("서울특별시 영등포구 영등포로19길 7-1")
                    .accomRating(5.0)
                    .accomWishlistCount(12)
                    .totalReviewNum(127)
                    .latitude(37.5228)
                    .longitude(126.8927)
                    .checkIn(LocalTime.of(15, 0))
                    .checkOut(LocalTime.of(11, 0))
                    .lowPrice(12000)
                    .reserveRange(60)
                    .build());

            Accommodation accommodation2 = accommodationRepository.save(Accommodation.builder()
                    .accomName("대구 팔공산 스타탄생 드라이브인")
                    .accomTel("050350521568")
                    .accomCategory(guestHouse)
                    .region(region)
                    .accomAddress("대구광역시 동구 파계로138길 36")
                    .accomRating(4.2)
                    .accomWishlistCount(12)
                    .totalReviewNum(127)
                    .latitude(37.5228)
                    .longitude(126.8927)
                    .lowPrice(11000)
                    .reserveRange(60)
                    .build());

            Accommodation accommodation3 = accommodationRepository.save(Accommodation.builder()
                    .accomName("인천(석남동) 뱅크")
                    .accomTel("050350521568")
                    .accomCategory(penssion)
                    .region(region)
                    .accomAddress("인천광역시 서구 염곡로 250")
                    .accomRating(3.6)
                    .accomWishlistCount(12)
                    .totalReviewNum(127)
                    .latitude(37.5228)
                    .longitude(126.8927)
                    .lowPrice(13000)
                    .reserveRange(60)
                    .build());

            Accommodation accommodation4 = accommodationRepository.save(Accommodation.builder()
                    .accomName("제주 탑아일랜드 호텔")
                    .accomTel("050350521568")
//                    .accomCategory(AccomCategory.GUESTHOUSE)
                    .region(region)
                    .accomAddress("제주특별자치도 제주시 용남1길 47")
                    .accomRating(2.1)
                    .accomWishlistCount(12)
                    .totalReviewNum(127)
                    .latitude(37.5228)
                    .longitude(126.8927)
                    .lowPrice(20000)
                    .reserveRange(60)
                    .build());

            Accommodation accommodation5 = accommodationRepository.save(Accommodation.builder()
                    .accomName("포항 씨엔스톤펜션")
                    .accomTel("050350521568")
//                    .accomCategory(AccomCategory.GUESTHOUSE)
                    .region(region)
                    .accomAddress("경상북도 포항시 북구 청하면 해안로1918번길34-1")
                    .accomRating(1.2)
                    .accomWishlistCount(12)
                    .totalReviewNum(127)
                    .latitude(37.5228)
                    .longitude(126.8927)
                    .lowPrice(20000)
                    .checkIn(LocalTime.of(15, 0))
                    .checkOut(LocalTime.of(11, 0))
                    .reserveRange(60)
                    .build());

            /** 숙소시설 등록 **/
            facilitiesRepository.save(Facilities.builder()
                    .facility(Facility.TWOBED)
                    .faciltiesOriginalFileName("image3.jpg")
                    .faciltiesSaveFilename("image3.jpg")
                    .build());

            /** 숙소이미지 등록 **/
            accommodationImageRepository.save( AccomImage.builder()
                    .accomOriginFilename("image4.jpg")
                    .accomSaveFilename("image1.jpg")
                    .accommodation(accommodation)
                    .build());

            accommodationImageRepository.save( AccomImage.builder()
                    .accomOriginFilename("image5.jpg")
                    .accomSaveFilename("image1.jpg")
                    .accommodation(accommodation2)
                    .build());

            accommodationImageRepository.save( AccomImage.builder()
                    .accomOriginFilename("image6.jpg")
                    .accomSaveFilename("image1.jpg")
                    .accommodation(accommodation3)
                    .build());

            accommodationImageRepository.save( AccomImage.builder()
                    .accomOriginFilename("image7.jpg")
                    .accomSaveFilename("image1.jpg")
                    .accommodation(accommodation4)
                    .build());

            accommodationImageRepository.save( AccomImage.builder()
                    .accomOriginFilename("image8.jpg")
                    .accomSaveFilename("image1.jpg")
                    .accommodation(accommodation5)
                    .build());


            /** 객실등록 **/
            Room room = roomRepository.save(Room.builder()
                    .accommodation(accommodation)
                    .roomName("아폴로룸")
                    .defaultGuest(2)
                    .maxGuest(10)
                    .price(70000)
                    .roomInfo("임시 객실정보 입니다")
                    .build());

            /** 객실 이미지 등록 **/
            roomImageRepository.save(RoomImage.builder()
                    .roomOriginFileName("image3.jpg")
                    .roomSaveFileName("image3.jpg")
                    .room(room)
                    .build());

            /** 객실 오락시설 등록 **/
            Amenities amenities = amenitiesRepository.save(Amenities.builder()
                    .amenitiesName("족구장")
                    .build());

            /** 객실 서비스 등록 **/
            serviceRepository.save(Service.builder()
                    .serviceName("수영장")
                    .build());


            /**위시리스트 등록**/
//            wishlistRepository.save(WishList.builder()
//                    .member(member)
//                    .accommodation(accommodation)
//                    .build());

            for (int i=0; i < 100; i++) {



                Accommodation build = Accommodation.builder()
                        .accomName("보령(대천) 너울펜션" + i)
                        .accomTel("050350577805")
                        .accomCategory(motel)
                        .region(region)
                        .accomAddress("충청남도 보령시 해수욕장13길 10-20" + i)
                        .accomRating(4.4)
                        .accomWishlistCount(110)
                        .totalReviewNum(1103)
                        .latitude(36.3196)
                        .longitude(126.5092)
                        .lowPrice(i+(500))
                        .reserveRange(60)
                        .checkIn(LocalTime.of(15, 0))
                        .checkOut(LocalTime.of(11, 0))
                        .build();

                Accommodation save = accommodationRepository.save(build);

                AccomImage image = accommodationImageRepository.save(AccomImage.builder()
                        .accomOriginFilename("image" + i + ".jpg")
                        .accomSaveFilename("image4.jpg")
                        .accommodation(save)
                        .build());


            }
//
//                WishList build1 = WishList.builder()
//                        .member(member)
//                        .accommodation(save)
//                        .build();
//                wishlistRepository.save(build1);
//            }


            /** 장바구니 등록 **/
            Cart cart = cartRepository.save(Cart.builder()
                    .member(member)
                    .room(room)
                    .reserveUseStartDate(LocalDate.of(2022, 11, 20))
                    .reserveUseEndDate(LocalDate.of(2022, 11, 22))
                    .reservePrice(room.getPrice())
                    .reserveAdultCount(3)
                    .reserveChildCount(1)
                    .build());

            /** 예약 등록 **/
            Orders order = Orders.builder()
                    .ordersTel(member.getMemberTel())
                    .ordersName(member.getNickname())
                    .ordersDate(LocalDate.now())
                    .ordersPrice(room.getPrice())
                    .ordersType("card")
                    .ordersStatus("결제완료")
                    .member(member)
                    .impUid("def")
                    .build();

            OrdersDetail ordersDetail = OrdersDetail.builder()
                    .member(cart.getMember())
                    .accommodation(cart.getAccommodation())
                    .room(cart.getRoom())
                    .orders(order)
                    .reserveStatus(ReserveStatus.READY)
                    .reserveUseStartDate(cart.getReserveUseStartDate())
                    .reserveUseEndDate(cart.getReserveUseEndDate())
                    .reservePrice(cart.getReservePrice())
                    .reserveAdultCount(cart.getReserveAdultCount())
                    .reserveChildCount(cart.getReserveChildCount())
                    .build();

            order.getReservation().add(ordersDetail);
            member.getOrders().add(order);

//            ordersDetailRepository.save(ordersDetail);
            ordersRepository.save(order);


            객실예약정보_입력(member.getMemberNum());

            /** 주문 등록 **/


            /** 댓글 등록 **/
            Review review = reviewRepository.save(Review.builder()
                    .member(member)
                    .reviewCreatedDate(LocalDateTime.now())
                    .reviewModifyDate(LocalDateTime.now())
                    .reviewRating(2.73)
                    .reviewContent("너무별로에요")
                    .build());


            /** 댓글 이미지 등록 **/
            reviewImageRepository.save(ReviewImage.builder()
                    .review(review)
                    .reviewOriginalFileName("Original1")
                    .reviewSaveFileName("Svae1")
                    .build());

            /** 리플 **/
            replyRepository.save(Reply.builder()
                    .member(member)
                    .review(review)
                    .content("언제나 최선을 다하겠습니다")
                    .replyCreatedDate(LocalDateTime.now())
                    .replyModifyDate(LocalDateTime.now())
                    .build());


            /** 매핑테이블들 **/


        }

        public void dbInit3(){
            /**회원등록**/
            Member member = memberRepository.save(Member.builder()
                    .email("bbb1@naver.com")
                    .pwd("1234")
                    .memberTel("010-5555-2323")
                    .nickname("정영진")
                    .memberOriginalFileName("Originl")
                    .memberSaveFileName("save")
                    .joinDate(LocalDate.of(2021,9,27))
                    .role(Role.MEMBER)
                    .build());
        }

        public void insertReserve(){

            /**회원등록**/
            Member member = memberRepository.save(Member.builder()
                    .email("a@com")
                    .pwd("1111")
                    .memberTel("010-9876-5432")
                    .nickname("토에이")
                    .memberOriginalFileName("Originl")
                    .memberSaveFileName("save")
                    .joinDate(LocalDate.now())
                    .role(Role.MEMBER)
                    .build());


            /**지역 등록 **/
            Region region = regionRepository.save(Region.builder()
                    .region(RegionType.JEJU)
                    .regionParentNum(RegionType.JEJU)
                    .build());



            /**숙소 등록**/
            Accommodation accommodation = accommodationRepository.save(Accommodation.builder()
                    .accomName("제주도 라르고 게스트하우스")
                    .accomTel("01045020614")
                    .region(region)
                    .accomAddress("제주도 서귀포시 성산읍 13길 10")

                    .accomRating(3.9)
                    .accomWishlistCount(100)
                    .totalReviewNum(238)
                    .latitude(36.3196)
                    .longitude(126.5092)
                    .checkIn(LocalTime.of(13, 0))
                    .checkOut(LocalTime.of(12, 0))
                    .lowPrice(33000)
                    .reserveRange(14)
                    .build());

            accommodationImageRepository.save( AccomImage.builder()
                    .accomOriginFilename("image9.jpg")
                    .accomSaveFilename("image1.jpg")
                    .accommodation(accommodation)
                    .build());

            Room room1 = roomRepository.save(Room.builder()
                    .accommodation(accommodation)
                    .roomName("우리 집 같은 내방룸")
                    .defaultGuest(2)
                    .maxGuest(2)
                    .stayStartDate(LocalDate.now())
                    .stayEndDate(LocalDate.of(2022,10, 28))
                    .price(43000)
                    .roomInfo("임시 객실정보 입니다")
                    .build());

            Room room2 = roomRepository.save(Room.builder()
                    .accommodation(accommodation)
                    .roomName("너네집 차가운 방")
                    .defaultGuest(2)
                    .maxGuest(3)
                    .stayStartDate(LocalDate.now())
                    .stayEndDate(LocalDate.of(2022,10, 28))
                    .price(65000)
                    .roomInfo("임시 객실정보 입니다")
                    .build());

            Room room3 = roomRepository.save(Room.builder()
                    .accommodation(accommodation)
                    .roomName("언제나 눕게 되는 방")
                    .defaultGuest(2)
                    .maxGuest(4)
                    .stayStartDate(LocalDate.now())
                    .stayEndDate(LocalDate.of(2022,10, 28))
                    .price(34000)
                    .roomInfo("임시 객실정보 입니다")
                    .build());

            Room room4 = roomRepository.save(Room.builder()
                    .accommodation(accommodation)
                    .roomName("너와 나의 연결방")
                    .defaultGuest(2)
                    .maxGuest(4)
                    .stayStartDate(LocalDate.now())
                    .stayEndDate(LocalDate.of(2022,10, 28))
                    .price(82000)
                    .roomInfo("임시 객실정보 입니다")
                    .build());

            Integer plusDay = 0;

            Orders order = makeOrder(member, plusDay++);
            Orders order1 = makeOrder(member, plusDay++);
            Orders order2 = makeOrder(member, plusDay++);
            Orders order3 = makeOrder(member, plusDay++);
            Orders order4 = makeOrder(member, plusDay++);
            Orders order5 = makeOrder(member, plusDay++);
            Orders order6 = makeOrder(member, plusDay++);
            Orders order7 = makeOrder(member, plusDay++);
            Orders order8 = makeOrder(member, plusDay++);
            Orders order9 = makeOrder(member, plusDay++);
            Orders order10 = makeOrder(member, plusDay++);
            Orders order11 = makeOrder(member, plusDay++);
            Orders order12 = makeOrder(member, plusDay++);

            makeOrderDetail(member, room1, ordersRepository.save(order));
            makeOrderDetail(member, room1, ordersRepository.save(order1));
            makeOrderDetail(member, room2, ordersRepository.save(order2));
            makeOrderDetail(member, room2, ordersRepository.save(order3));
            makeOrderDetail(member, room2, ordersRepository.save(order4));
            makeOrderDetail(member, room3, ordersRepository.save(order5));
            makeOrderDetail(member, room3, ordersRepository.save(order6));
            makeOrderDetail(member, room3, ordersRepository.save(order7));
            makeOrderDetail(member, room4, ordersRepository.save(order8));
            makeOrderDetail(member, room4, ordersRepository.save(order9));
            makeOrderDetail(member, room1, ordersRepository.save(order10));
            makeOrderDetail(member, room1, ordersRepository.save(order11));
            makeOrderDetail(member, room1, ordersRepository.save(order12));


            member.getOrders().add(order);

//            ordersDetailRepository.save(ordersDetail);
            ordersRepository.save(order);


            객실예약정보_입력(member.getMemberNum());

        }

        public Orders makeOrder(Member member, Integer day){
            return Orders.builder()
                    .ordersTel(member.getMemberTel())
                    .ordersName(member.getNickname())
                    .ordersDate(LocalDate.now().plusDays(day))
                    .ordersPrice(35000)
                    .ordersType("card")
                    .ordersStatus("결제완료")
                    .member(member)
                    .build();
        }

        public OrdersDetail makeOrderDetail(Member member, Room room, Orders orders){

            OrdersDetail od = OrdersDetail.builder()
                    .member(member)
                    .accommodation(room.getAccommodation())
                    .room(room)
                    .orders(orders)
                    .reserveStatus(ReserveStatus.READY)
                    .reserveUseStartDate(room.getStayStartDate())
                    .reserveUseEndDate(room.getStayEndDate())
                    .reservePrice(room.getPrice())
                    .reserveAdultCount(2)
                    .reserveChildCount(2)
                    .build();
            orders.getReservation().add(od);
            return od;
        }



        public void 객실예약정보_입력(Long memberId) {

            Optional<Orders> optionOrders = ordersRepository.findById(memberId);

            if (optionOrders.isEmpty()) {
                new NullPointerException("주문이 없습니다.");
            }
            Orders orders = optionOrders.get();

            for (Reservation reservation : orders.getReservation()) {
                LocalDate ldStart = reservation.getReserveUseStartDate();
                LocalDate ldEnd = reservation.getReserveUseEndDate();

                Long days = ChronoUnit.DAYS.between(ldStart, ldEnd);

                for (Long i = 0L; i < days; i++) {
                    AvailableDate newDate = AvailableDate.builder()
                            .date(ldStart.plusDays(i))
                            .room(reservation.getRoom())
                            .build();
                    reservation.getRoom().getAvailableDate().add(newDate);
                }
            }
        }

    }
}







