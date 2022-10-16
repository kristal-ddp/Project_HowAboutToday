package com.phoenix.howabouttoday.payment.service;

import com.phoenix.howabouttoday.accom.entity.AccomImage;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.repository.AccommodationRepository;
import com.phoenix.howabouttoday.member.entity.Code;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.payment.AccomCategory;
import com.phoenix.howabouttoday.payment.Orders;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.reserve.domain.Reservation.ReserveStatus;
import com.phoenix.howabouttoday.room.entity.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class ObjectGenerator {

//    @Autowired
//    private AccommodationRepository accommodationRepository;
////    private final AccommodationImageRepository accommodationImageRepository;


    public static void main(String[] args) {
        ObjectGenerator test = new ObjectGenerator();
        test.createMember();
    }


    public String[] randomString(){

        String randAlphabet[] = new String[4];
        randAlphabet[0] = "";
        randAlphabet[1] = "";
        randAlphabet[2] = "";
        randAlphabet[3] = "";

        for (int i = 0; i < 4; i++) {
            randAlphabet[0]+= (char)(Math.random() * 26 + 97);
            randAlphabet[1]+= (char)(Math.random() * 26 + 97);
            randAlphabet[2]+= (char)(Math.random() * 26 + 97);
            randAlphabet[3]+= (char)(Math.random() * 26 + 97);
        }

        return randAlphabet;
    }
    private String randomTel(){
        String randTel =  "";

        for (int i = 0; i < 11; i++) {
            randTel += (char)(Math.floor(Math.random() * 48 + 10));
        }
        return randTel;
    }


    public Member createMember(){
        String[] randValue = randomString();

        Member member = Member.builder()
                .email(randValue[0] + "@" + randValue[1] + ".com")
                .pwd("1111")
                .nickname(randValue[2])
                .memberTel(randomTel())
                .memberCode(Code.MEMBER)
                .joinDate(LocalDateTime.now())
                .withdrawdate(LocalDateTime.now())
                .memberOriginalFileName(randValue[3])
                .memberSaveFileName(randValue[3])
                .build();

        return member;
    }

    public AccomImage createImage(){
        String imageNumber = String.valueOf(Math.floor(Math.random() * 200));

        AccomImage image = AccomImage.builder()
                .accomOriginFilename("image" + imageNumber + ".jpg")
                .accomSaveFilename("image" + imageNumber + ".jpg")
                .build();

        return image;
    }


//
//    @Test
//    public void 생성_매핑_테스트() {
//
//
//        Accommodation newMember = Accommodation.builder()
//                .accomName("보령(대천) 너울펜션")
//                .accomTel("050350577805")
//                .accomCategoryName(AccomCategory.PENSION)
//                .regionNum(8)
//                .accomAddress("충청남도 보령시 해수욕장13길 10-20")
//                .accomRating(4.4)
//                .accomWishlistCount(110)
//                .totalReviewNum(1103)
//                .latitude(36.3196)
//                .longitude(126.5092)
//                .lowPrice(45000)
//                .reserveRange(60)
//                .accommodationImage(new ArrayList<AccomImage>())
//                .build();
//
//
//        image.setAccommodation(newMember);
//        image1.setAccommodation(newMember);
//        newMember.getAccommodationImage().add(image);
//        newMember.getAccommodationImage().add(image1);
//
//
//        accommodationRepository.save(newMember);
//
//
//        Accommodation test = accommodationRepository.findByAccomNum(newMember.getAccomNum());
//
//        Assertions.assertThat(test.getAccomName()).isEqualTo(newMember.getAccomName());
//        Assertions.assertThat(test.getAccommodationImage().size()).isEqualTo(2);
//        Assertions.assertThat(test.getAccomCategoryName()).isEqualTo(newMember.getAccomCategoryName());
//
//        System.out.println(test.getAccomName());
//        System.out.println(test.getAccommodationImage().size());
//        System.out.println(test.getAccomCategoryName());
//    }
//
//    @Test
//    public void 장바구니에서_결제로_넘기는_테스트(){
//
//
//        Member member = Member.builder()
//                .email("ingn@nate.com")
//                .pwd("1111")
//                .nickname("noscarna")
//                .memberTel("01045020614")
//                .memberCode(Code.MEMBER)
//                .joinDate(LocalDateTime.now())
//                .withdrawdate(LocalDateTime.now())
//                .memberOriginalFileName("Origin")
//                .memberSaveFileName("save")
//                .build();
//
//
//        Accommodation acco = Accommodation.builder()
//                .accomName("보령(대천) 너울펜션")
//                .accomTel("050350577805")
//                .accomCategoryName(AccomCategory.PENSION)
//                .regionNum(8)
//                .accomAddress("충청남도 보령시 해수욕장13길 10-20")
//                .accomRating(4.4)
//                .accomWishlistCount(110)
//                .totalReviewNum(1103)
//                .latitude(36.3196)
//                .longitude(126.5092)
//                .lowPrice(45000)
//                .reserveRange(60)
//                .accommodationImage(new ArrayList<AccomImage>())
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
//                .accomCategoryName(AccomCategory.PENSION)
//                .regionNum(8)
//                .accomAddress("충청남도 보령시 해수욕장13길 10-20")
//                .accomRating(4.4)
//                .accomWishlistCount(110)
//                .totalReviewNum(1103)
//                .latitude(36.3196)
//                .longitude(126.5092)
//                .lowPrice(45000)
//                .reserveRange(60)
//                .accommodationImage(new ArrayList<AccomImage>())
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
//                .ordersDate("2022-10-13")
//                .ordersPrice(45000L+90000L)
//                .ordersType("카드")
//                .ordersStatus("이용 전")
//                .build();
//
//        newOrder.getReservation().add(cart);
//        newOrder.getReservation().add(cart2);
//
//        System.out.println("하아");
//    }
//
//    @Test
//    public void 객실에서_결제로_넘기는_테스트(){
//        Member member = Member.builder()
//                .email("ingn@nate.com")
//                .pwd("1111")
//                .nickname("noscarna")
//                .memberTel("01045020614")
//                .memberCode(Code.MEMBER)
//                .joinDate(LocalDateTime.now())
//                .withdrawdate(LocalDateTime.now())
//                .memberOriginalFileName("Origin")
//                .memberSaveFileName("save")
//                .build();
//
//
//        Accommodation acco = Accommodation.builder()
//                .accomName("보령(대천) 너울펜션")
//                .accomTel("050350577805")
//                .accomCategoryName(AccomCategory.PENSION)
//                .regionNum(8)
//                .accomAddress("충청남도 보령시 해수욕장13길 10-20")
//                .accomRating(4.4)
//                .accomWishlistCount(110)
//                .totalReviewNum(1103)
//                .latitude(36.3196)
//                .longitude(126.5092)
//                .lowPrice(45000)
//                .reserveRange(60)
//                .accommodationImage(new ArrayList<AccomImage>())
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


}
