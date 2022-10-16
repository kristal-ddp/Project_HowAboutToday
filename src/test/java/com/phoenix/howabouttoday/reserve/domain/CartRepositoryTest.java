package com.phoenix.howabouttoday.reserve.domain;

import com.phoenix.howabouttoday.accom.RegionType;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.entity.Region;
import com.phoenix.howabouttoday.accom.repository.AccommodationRepository;
import com.phoenix.howabouttoday.accom.repository.RegionRepository;
import com.phoenix.howabouttoday.member.entity.Code;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Reservation;
import com.phoenix.howabouttoday.room.entity.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class CartRepositoryTest {

    @Autowired CartRepository cartRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired RegionRepository regionRepository;
    @Autowired AccommodationRepository accommodationRepository;

    @Autowired RoomRepository roomRepository;
    @Test
    public void 카트_등록_조회() throws Exception{
        //given
        Member member = Member.builder()
                .email("yongjin")
                .pwd("1234")
                .nickname("정영진")
                .memberCode(Code.MEMBER).build();
        Member saveMember = memberRepository.save(member);
//        Optional<Member> byId = memberRepository.findById(saveMember.getMemberNum());
//        Member findMember = byId.get();
        Region region1 = Region.builder().region(RegionType.SEOUL).build();
        Region saveRegion = regionRepository.save(region1);
//        Optional<Region> byId1 = regionRepository.findById(saveRegion.getRegionNum());
//        Region findRegion = byId1.get();
        Accommodation accom1 = Accommodation.builder().accomName("숙소1").region(saveRegion).build();

        Accommodation saveAccom = accommodationRepository.save(accom1);
//        Optional<Accommodation> byId2 = accommodationRepository.findById(saveAccom.getAccomNum());
//        Accommodation findAccom = byId2.get();



        Room room1 = Room.builder()
                .roomName("깨끗한 방")
                .accommodation(saveAccom)
                .defaultGuest(2)
                .maxGuest(4)
                .price(45000)
                .roomInfo("고객에게 최선을 다합니다.")
                .build();

        Room saveRoom = roomRepository.save(room1);

        Cart cart1 = Cart.builder()
                .member(saveMember)
                .accommodation(saveAccom)
                .room(saveRoom)
                .reservePrice(8000)
                .reserveUseStartDate(LocalDate.now())
                .reserveUseEndDate(LocalDate.now())
                .build();
        Cart saveCart = cartRepository.save(cart1);

        Optional<Cart> byCart = cartRepository.findById(saveCart.getReserveNum());
        Cart findCart = byCart.get();

        Assertions.assertThat(findCart.getOrders()).isNull();


        //when

        //then
    }

}