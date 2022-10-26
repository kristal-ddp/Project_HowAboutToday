package com.phoenix.howabouttoday.payment.service;

import com.phoenix.howabouttoday.global.RegionType;
import com.phoenix.howabouttoday.accom.entity.AccomImage;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.entity.Region;
import com.phoenix.howabouttoday.accom.repository.AccommodationRepository;
import com.phoenix.howabouttoday.member.entity.Role;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.global.AccomCategory;
import com.phoenix.howabouttoday.room.entity.AvailableDate;
import com.phoenix.howabouttoday.payment.repository.OrdersRepository;
import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.reserve.domain.Reservation.ReserveStatus;
import com.phoenix.howabouttoday.room.entity.Room;
import com.phoenix.howabouttoday.room.entity.RoomImage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ObjectGenerator {

    private AccommodationRepository accommodationRepository;


    private OrdersRepository orderRepository;
////    private final AccommodationImageRepository accommodationImageRepository;


//    public static void main(String[] args) {
//        ObjectGenerator test = new ObjectGenerator();
//        test.createMember();
//    }

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
                .role(Role.MEMBER)
                .joinDate(LocalDate.now())
                .withdrawdate(LocalDate.now())
                .memberOriginalFileName(randValue[3])
                .memberSaveFileName(randValue[3])
                .build();

        return member;
    }

    public Accommodation createAccommodation(){
        String[] randValue = randomString();

        Accommodation room = Accommodation.builder()
                .accomName("보령(대천) 너울펜션")
                .accomTel("050350577805")
                .accomCategory(AccomCategory.PENSION)
                .region(getRandomRegion())
                .accomAddress("충청남도 보령시 해수욕장13길 10-20")
                .accomRating(4.4)
                .accomWishlistCount(110)
                .totalReviewNum(1103)
                .latitude(36.3196)
                .longitude(126.5092)
                .lowPrice(45000)
                .reserveRange(60)
                .build();
        return room;
    }

    public Room createRoom(Accommodation accom){
        String[] randValue = randomString();

        Room room = Room.builder()
                .roomName(randValue[0] + "한 방")
                .defaultGuest((int)(Math.random() * 2 + 1))
                .maxGuest((int)(Math.random() * 3 + 2))
                .price(35000 + (int)(Math.random() * 10000 + 5000))
                .roomInfo("고객에게 최선을 다합니다.")
                .accommodation(accom)
                .build();

        return room;
    }


    public Region getRandomRegion(){


        Integer number = (int)(Math.floor(Math.random() * 16 + 1));


        Region region = Region.builder()
                .region(RegionType.values()[number])
                .regionParentNum(RegionType.EMPTY)
                .build();

        return region;
    }

    public AccomImage createAccomImage(Accommodation accom){
        String imageNumber = String.valueOf(Math.round(Math.random() * 200));

        AccomImage image = AccomImage.builder()
                .accomOriginFilename("image" + imageNumber + ".jpg")
                .accomSaveFilename("image" + imageNumber + ".jpg")
                .accommodation(accom)
                .build();

        return image;
    }

    public RoomImage createRoomImage(Room room){
        String imageNumber = String.valueOf(Math.round(Math.random() * 200));

        RoomImage image = RoomImage.builder()
                .roomOriginFileName("image" + imageNumber + ".jpg")
                .roomSaveFileName("image" + imageNumber + ".jpg")
                .room(room)
                .build();

        return image;
    }

    public Orders makeTestData(){
        String[] randValue = randomString();

        List<AvailableDate> newDate = new ArrayList<>(60);

        Room room = createRoom(null);

        Cart cart = Cart.builder()
                .member(createMember())
                .accommodation(createAccommodation())
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
                .ordersDate(LocalDate.now())
                .ordersPrice(45000+90000)
                .ordersType("카드")
                .ordersStatus("이용 전")
                .build();

        return newOrder;
    }
}
