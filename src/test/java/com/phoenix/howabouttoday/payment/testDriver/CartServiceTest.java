package com.phoenix.howabouttoday.payment.testDriver;

import com.phoenix.howabouttoday.global.AccomCategory;
import com.phoenix.howabouttoday.global.RegionType;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.entity.Region;
import com.phoenix.howabouttoday.member.entity.Code;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.reserve.domain.CartRepository;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.reserve.domain.Reservation.ReserveStatus;
import com.phoenix.howabouttoday.room.entity.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    //디비에 Cart정보를 저장
    public void saveData(){
        cartRepository.save(createCartData());
        List<Cart> cartData =  cartRepository.findAll();

        Assertions.assertThat(cartData.size()).isEqualTo(1);
    }
    @Test
    public Cart createCartData(){

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
                .joinDate(LocalDate.now())
                .withdrawdate(LocalDate.now())
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

        return cart;
    }
}