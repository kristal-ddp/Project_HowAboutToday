package com.phoenix.howabouttoday.payment.service;


import com.phoenix.howabouttoday.global.RegionType;
import com.phoenix.howabouttoday.accom.entity.AccomImage;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.entity.Region;
import com.phoenix.howabouttoday.accom.repository.AccommodationImageRepository;
import com.phoenix.howabouttoday.accom.repository.AccommodationRepository;
import com.phoenix.howabouttoday.accom.repository.RegionRepository;
import com.phoenix.howabouttoday.member.entity.Code;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.payment.entity.OrdersDetail;
import com.phoenix.howabouttoday.payment.repository.OrdersDetailRepository;
import com.phoenix.howabouttoday.payment.repository.AvailableDateRepository;
import com.phoenix.howabouttoday.payment.repository.OrdersRepository;
import com.phoenix.howabouttoday.global.AccomCategory;
import com.phoenix.howabouttoday.room.entity.AvailableDate;
import com.phoenix.howabouttoday.reserve.domain.CartRepository;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Reservation;
import com.phoenix.howabouttoday.reserve.domain.Reservation.ReserveStatus;
import com.phoenix.howabouttoday.room.entity.Room;
import com.phoenix.howabouttoday.room.entity.RoomImage;
import com.phoenix.howabouttoday.room.repository.RoomImageRepository;
import com.phoenix.howabouttoday.room.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@SpringBootTest
@Transactional
public class DataInsertTest {



    MemberRepository memberRepository;
    AccommodationRepository accommodationRepository;
    RoomRepository roomRepository;
    AvailableDateRepository availableDateRepository;
    RoomImageRepository roomImageRepository;
    AccommodationImageRepository accommodationImageRepository;
    RegionRepository regionRepository;
    CartRepository cartRepository;
    OrdersRepository ordersRepository;
    OrdersDetailRepository ordersDetailRepository;

    @Autowired
    public DataInsertTest(MemberRepository memberRepository, AccommodationRepository accommodationRepository, RoomRepository roomRepository, AvailableDateRepository availableDateRepository, RoomImageRepository roomImageRepository, AccommodationImageRepository accommodationImageRepository, RegionRepository regionRepository, CartRepository cartRepository, OrdersRepository ordersRepository, OrdersDetailRepository ordersDetailRepository) {
        this.memberRepository = memberRepository;
        this.accommodationRepository = accommodationRepository;
        this.roomRepository = roomRepository;
        this.availableDateRepository = availableDateRepository;
        this.roomImageRepository = roomImageRepository;
        this.accommodationImageRepository = accommodationImageRepository;
        this.regionRepository = regionRepository;
        this.cartRepository = cartRepository;
        this.ordersRepository = ordersRepository;
        this.ordersDetailRepository = ordersDetailRepository;
    }


    private ObjectGenerator objectGenerator = new ObjectGenerator();


    @Test
    public void insertMember(){

        List<Member> memberList = new ArrayList<>();

//        # email, join_date, member_code, member_original_file_name, member_save_file_name, member_tel, nickname, pwd, withdrawdate
        memberList.add(new Member("tellus@yahoo.edu", "Calista Hicks", "1111", "010-1632-1860" , Code.MEMBER, LocalDate.now(), LocalDate.now(), "image0.jpg", "image1.jpg"));
        memberList.add(new Member("adipiscingligula@hotmail.ca", "Cullen Burgess", "1111", "010-1632-1860" , Code.MEMBER, LocalDate.now(), LocalDate.now(), "image2.jpg", "image3.jpg"));
        memberList.add(new Member("vulputateem@protonmail.org", "Calista Hicks", "1111", "010-1632-1860" , Code.MEMBER, LocalDate.now(), LocalDate.now(), "image4.jpg", "image5.jpg"));
        memberList.add(new Member("itamet@icloud.edu", "Clarke Reed", "1111", "010-1632-1860" , Code.MEMBER, LocalDate.now(), LocalDate.now(), "image6.jpg", "image7.jpg"));
        memberList.add(new Member("tellus@yahoo.edu", "Barrett Bell", "1111", "010-1632-1860" , Code.MEMBER, LocalDate.now(), LocalDate.now(), "image8.jpg", "image9.jpg"));

        memberRepository.saveAll(memberList);

        //Assertions.assertThat(memberRepository.findAll().size()).isEqualTo(5);

//        return memberList;
    }

    Integer REGION_COUNT= 16;

    @Test
    @Rollback(value = false)
    public void makeData(){
        Supplier<Integer> rn = () -> (int)(Math.floor(Math.random() * REGION_COUNT + 1)); //랜덤한 지역값 가져오기

        List<Region> region = insertRegion();

        insertAcccom_room(region.get(rn.get()));
        insertAcccom_room(region.get(rn.get()));
        insertAcccom_room(region.get(rn.get()));
        insertAcccom_room(region.get(rn.get()));
        insertAcccom_room(region.get(rn.get()));

        insertMember();

        addToCartTest(1L, 2);
        addToCartTest(2L, 3);
        addToCartTest(3L, 1);

        payment();

        객실예약정보_입력();

//        Assertions.assertThat(ordersRepository.findAll().size()).isEqualTo(1);

        Optional<Member> optionalMember = memberRepository.findById(1L);
        Member member1 = optionalMember.get();

        List<Cart> cartList = cartRepository.findAllByMember_MemberNum(member1.getMemberNum());

        for (Cart cart : cartList) {
            System.out.println("cart.getReserveNum() = " + cart.getReserveNum());
        }

//        cartRepository.deleteAll();
        if (optionalMember.isEmpty()){
            new NullPointerException("회원 정보가 없습니다.");
        }
        Member member = optionalMember.get();


        System.out.println("잘 되니?");

//        memberList.get(0);
    }

    @Test
    public void 멤버db테스트(){
        System.out.println(memberRepository.findAll().size());
    }

    //결제가 완료되어서 예약이 됐으면 그 정보를 룸에 저장해서 더이상 해당 객실의 해당 날짜는 예약이 불가능하도록 설정
    public void 객실예약정보_입력(){

        Optional<Orders> optionOrders = ordersRepository.findById(1L);

        if (optionOrders.isEmpty()){
            new NullPointerException("주문이 없습니다.");
        }
        Orders orders = optionOrders.get();

        for (Reservation reservation:orders.getReservation()) {
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

    public void payment(){

        List<Cart> carts = cartRepository.findAll();

        Integer totalPrice = 0;

        for (Cart data : carts) {
            totalPrice += data.getReservePrice();
        }

        Optional<Member> optionalMember = memberRepository.findById(1L);

        if (optionalMember.isEmpty()){
            new NullPointerException("회원 정보가 없습니다.");
        }
        Member member = optionalMember.get();

        Orders order = Orders.builder()
                .ordersTel("01045020614")
                .ordersName("김영운")
                .ordersDate(LocalDate.now())
                .ordersPrice(totalPrice)
                .ordersType("card")
                .ordersStatus("결제완료")
                .member(member)
                .build();



        for (Cart data : carts) {
            OrdersDetail ordersDetail = OrdersDetail.builder()
                    .member(data.getMember())
                    .accommodation(data.getAccommodation())
                    .room(data.getRoom())
                    .orders(order)
                    .reserveStatus(ReserveStatus.READY)
                    .reserveUseStartDate(data.getReserveUseStartDate())
                    .reserveUseEndDate(data.getReserveUseEndDate())
                    .reservePrice(data.getReservePrice())
                    .reserveAdultCount(data.getReserveAdultCount())
                    .reserveChildCount(data.getReserveChildCount())
                    .build();
            ordersDetailRepository.save(ordersDetail);
//            order.getReservation().add(ordersDetail);
        }
        ordersRepository.save(order);

    }




    @Test
    public void addToCartTest(Long accomNum, Integer roomNum){

        Supplier<Integer> rn = () -> (int)(Math.floor(Math.random() * REGION_COUNT + 1)); //랜덤한 지역값 가져오기

//        Member member = new Member("tellus@yahoo.edu", "Calista Hicks", "1111", "010-1632-1860" , Code.MEMBER, LocalDate.now(), LocalDate.now(), "image0.jpg", "image1.jpg");
       Optional<Member> optionalMember = memberRepository.findById(1L);
       Member member = null;
       if (!optionalMember.isEmpty()){
           member = optionalMember.get();
       }

        Accommodation accom = accommodationRepository.findByAccomNum(accomNum);

        Room room = accom.getRoom().get(roomNum);

        Cart rowCart = Cart.builder()
                .member(member)
                .accommodation(accom)
                .room(room)
                .orders(null)
                .reserveStatus(ReserveStatus.READY)
                .reserveUseStartDate(LocalDate.now())
                .reserveUseEndDate(LocalDate.of(2022, 10, 19))
                .reservePrice(room.getPrice())
                .reserveAdultCount(1)
                .reserveChildCount(0)
                .build();

        cartRepository.save(rowCart);
    }


    @Test
    public List<Region> insertRegion(){

        List<Region> region = new ArrayList<>();

        for (int i = 0; i < REGION_COUNT + 1; i++) {
            region.add(Region.builder()
                    .region(RegionType.values()[i])
                    .regionParentNum(RegionType.EMPTY)
                    .build());
        }

        regionRepository.saveAll(region);
        return region;
    }

    @Test
    public void insertAcccom_room(Region region){

        String[] randValue = randomString();


        Accommodation accom = Accommodation.builder()
                .accomName(randValue[0] + "보령(대천) 너울펜션")
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
                .reserveRange(59)
                .build();

        region.getAccommodation().add(accom);

        Room[] rooms = new Room[4];

        rooms[0] = objectGenerator.createRoom(accom);
        rooms[1] = objectGenerator.createRoom(accom);
        rooms[2] = objectGenerator.createRoom(accom);
        rooms[3] = objectGenerator.createRoom(accom);


        for (Room room: rooms) {
            RoomImage roomImage0 = objectGenerator.createRoomImage(room);
            RoomImage roomImage1 = objectGenerator.createRoomImage(room);
            RoomImage roomImage2 = objectGenerator.createRoomImage(room);
            RoomImage roomImage3 = objectGenerator.createRoomImage(room);

            room.getRoomImageList().add(roomImage0);
            room.getRoomImageList().add(roomImage1);
            room.getRoomImageList().add(roomImage2);
            room.getRoomImageList().add(roomImage3);

            accom.getRoom().add(room);
            accom.getRoom().add(room);
            accom.getRoom().add(room);
            accom.getRoom().add(room);
        }

        AccomImage accomImage = objectGenerator.createAccomImage(accom);
        AccomImage accomImage1 = objectGenerator.createAccomImage(accom);
        AccomImage accomImage2 = objectGenerator.createAccomImage(accom);
        AccomImage accomImage3 = objectGenerator.createAccomImage(accom);

        accom.getAccommodationImage().add(accomImage);
        accom.getAccommodationImage().add(accomImage1);
        accom.getAccommodationImage().add(accomImage2);
        accom.getAccommodationImage().add(accomImage3);

        accommodationRepository.save(accom);

//        Assertions.assertThat(accommodationRepository.findAll().size()).isEqualTo(1);
//        Assertions.assertThat(roomRepository.findAll().size()).isEqualTo(4);
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


}
