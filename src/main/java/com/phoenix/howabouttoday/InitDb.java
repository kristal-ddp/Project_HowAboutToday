package com.phoenix.howabouttoday;


import com.phoenix.howabouttoday.accom.entity.*;
import com.phoenix.howabouttoday.accom.repository.*;
import com.phoenix.howabouttoday.board.entity.Reply;
import com.phoenix.howabouttoday.board.entity.Review;
import com.phoenix.howabouttoday.board.entity.ReviewImage;
import com.phoenix.howabouttoday.board.repository.*;
import com.phoenix.howabouttoday.global.AccomCategory;
import com.phoenix.howabouttoday.global.RegionType;
import com.phoenix.howabouttoday.member.entity.Code;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import com.phoenix.howabouttoday.member.wishlist.WishList;
import com.phoenix.howabouttoday.member.wishlist.WishlistRepository;
import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.payment.entity.OrdersDetail;

import com.phoenix.howabouttoday.payment.repository.OrdersDetailRepository;
import com.phoenix.howabouttoday.payment.repository.OrdersRepository;


import com.phoenix.howabouttoday.reserve.domain.CartRepository;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Reservation;
import com.phoenix.howabouttoday.reserve.domain.Reservation.ReserveStatus;
import com.phoenix.howabouttoday.room.dto.AvailableDate;
import com.phoenix.howabouttoday.room.entity.Amenities;
import com.phoenix.howabouttoday.room.entity.Room;
import com.phoenix.howabouttoday.room.entity.RoomImage;
import com.phoenix.howabouttoday.room.entity.Service;
import com.phoenix.howabouttoday.room.repository.AmenitiesRepository;
import com.phoenix.howabouttoday.room.repository.RoomImageRepository;
import com.phoenix.howabouttoday.room.repository.RoomRepository;
import com.phoenix.howabouttoday.room.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
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
        private final OrdersDetailRepository ordersDetailRepository;
        private final AccommodationImageRepository accommodationImageRepository;

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
                    .memberCode(Code.MEMBER)
                    .build());


            /**지역 등록 **/
            Region region = regionRepository.save(Region.builder()
                    .region(RegionType.SEOUL)
                    .regionParentNum(RegionType.SEOUL)
                    .build());

            /**숙소 등록**/
            Accommodation accommodation = accommodationRepository.save(Accommodation.builder()
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
                    .checkIn(LocalTime.of(15, 0))
                    .checkOut(LocalTime.of(11, 0))
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
                    .price(50000)
                    .roomInfo("임시 객실정보 입니다")
                    .build());

            Room room1 = roomRepository.save(Room.builder()
                    .accommodation(accommodation)
                    .roomName("너울펜션 디럭스룸")
                    .defaultGuest(2)
                    .maxGuest(10)
                    .price(70000)
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


            /** 객실 오락시설 등록 **/
            amenitiesRepository.save(Amenities.builder()
                    .amenitiesName("흡연장")
                    .build());

            /** 객실 서비스 등록 **/
            serviceRepository.save(Service.builder()
                    .serviceName("피트니스")
                    .build());

            /**위시리스트 등록**/
            wishlistRepository.save(WishList.builder()
                    .member(member)
                    .accommodation(accommodation)
                    .build());

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
                    .memberCode(Code.MEMBER)
                    .build());

            /**지역 등록 **/
            Region region = regionRepository.save(Region.builder()
                    .region(RegionType.BUSAN)
                    .regionParentNum(RegionType.BUSAN)
                    .build());

            /**숙소 등록**/
            Accommodation accommodation = accommodationRepository.save(Accommodation.builder()
                    .accomName("서울 아폴로 게스트하우스")
                    .accomTel("050350521568")
                    .accomCategory(AccomCategory.GUESTHOUSE)
                    .region(region)
                    .accomAddress("서울특별시 영등포구 영등포로19길 7-1")
                    .accomRating(5.0)
                    .accomWishlistCount(12)
                    .totalReviewNum(127)
                    .latitude(37.5228)
                    .longitude(126.8927)
                    .lowPrice(12000)
                    .reserveRange(60)
                    .build());

            Accommodation accommodation2 = accommodationRepository.save(Accommodation.builder()
                    .accomName("대구 팔공산 스타탄생 드라이브인")
                    .accomTel("050350521568")
                    .accomCategory(AccomCategory.GUESTHOUSE)
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
                    .accomCategory(AccomCategory.GUESTHOUSE)
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
                    .accomCategory(AccomCategory.GUESTHOUSE)
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
                    .accomCategory(AccomCategory.GUESTHOUSE)
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
            /*accommodationImageRepository.save( AccomImage.builder()
                    .accomOriginFilename("image1.jpg")
                    .accomSaveFilename("image1.jpg")
                    .accommodation(accommodation)
                    .build());*/

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
            amenitiesRepository.save(Amenities.builder()
                    .amenitiesName("족구장")
                    .build());

            /** 객실 서비스 등록 **/
            serviceRepository.save(Service.builder()
                    .serviceName("수영장")
                    .build());

            /**위시리스트 등록**/
            wishlistRepository.save(WishList.builder()
                    .member(member)
                    .accommodation(accommodation)
                    .build());

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
                    .memberCode(Code.MEMBER)
                    .build());
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







