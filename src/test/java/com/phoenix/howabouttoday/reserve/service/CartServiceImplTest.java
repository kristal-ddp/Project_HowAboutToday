package com.phoenix.howabouttoday.reserve.service;

import com.phoenix.howabouttoday.accom.repository.AccommodationRepository;
import com.phoenix.howabouttoday.accom.repository.RegionRepository;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import com.phoenix.howabouttoday.reserve.domain.CartRepository;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;

import com.phoenix.howabouttoday.room.repository.RoomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(false)
class CartServiceImplTest {



    @Autowired
    CartRepository cartRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    AccommodationRepository accommodationRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired CartService cartService;

    @Test
    public void 장바구니저장_테스트() throws Exception{
        //given
        ReserveForm rsForm = new ReserveForm();
        rsForm.setReserveUseStartDate(LocalDate.now());
        rsForm.setReserveUseEndDate(LocalDate.now());
        rsForm.setReserveAdultCount(2);
        
        Cart saveCart = cartService.save(1L, 2L, rsForm);

        Optional<Cart> byId = cartRepository.findById(saveCart.getReserveNum());
        Cart findCart = byId.get();

        assertThat(saveCart.getReserveNum()).isEqualTo(findCart.getReserveNum());

    }
    public void 장바구니_한줄삭제() throws Exception{
        //given
        cartService.deleteByNum(5L);

    }
    
    
    @Test
    public void 회원에대한_장바구니리스트_조회() throws Exception{
        //given
        List<CartDto.ResponseDto> responseMembers = cartService.getListMemberNum(1L);

        for (CartDto.ResponseDto responseMember : responseMembers) {
            System.out.println("responseMember= " + responseMember.toString());
        }



    }


    @Test





}