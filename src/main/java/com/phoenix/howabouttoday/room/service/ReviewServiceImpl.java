package com.phoenix.howabouttoday.room.service;

import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import com.phoenix.howabouttoday.payment.dto.OrdersDetailDTO;
import com.phoenix.howabouttoday.payment.dto.RoomReviewCreateRequestDTO;
import com.phoenix.howabouttoday.payment.dto.RoomReviewCreateResponseDTO;
import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.payment.entity.OrdersDetail;
import com.phoenix.howabouttoday.payment.enumType.ReviewResponseCode;
import com.phoenix.howabouttoday.payment.repository.OrdersDetailRepository;
import com.phoenix.howabouttoday.payment.repository.OrdersRepository;
import com.phoenix.howabouttoday.room.dto.*;
import com.phoenix.howabouttoday.room.entity.Review;
import com.phoenix.howabouttoday.room.entity.Room;
import com.phoenix.howabouttoday.room.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final RoomRepository roomRepository;
    private final OrdersDetailRepository ordersDetailRepository;
    private final MemberRepository memberRepository;
    private final RoomReviewRepository roomReviewRepository;

    @Override
    public RoomReviewCreateResponseDTO isPossibleWrite(SessionDTO sessionDTO, RoomReviewCreateRequestDTO roomReviewCreateRequestDTO) {

        if(!isMember(sessionDTO)){
            return new RoomReviewCreateResponseDTO(ReviewResponseCode.NOT_MEMBER);
        }
        if(!isPaid(sessionDTO.getMemberNum(), roomReviewCreateRequestDTO.getRoomNum())){
            return new RoomReviewCreateResponseDTO(ReviewResponseCode.NOT_RESERVE);
        }
        if(!withinTwoWeeks(sessionDTO.getMemberNum(), roomReviewCreateRequestDTO.getRoomNum())){
            return new RoomReviewCreateResponseDTO(ReviewResponseCode.OVER_TWO_WEEKS);
        }

        reviewSave(sessionDTO, roomReviewCreateRequestDTO);
//        reviewNumUp()

        return new RoomReviewCreateResponseDTO(ReviewResponseCode.WRITE_POSSIBLE);
    }

    @Override
    public List<OrdersDetailDTO> isExistOrderDetail(SessionDTO sessionDTO, Long roomNum) {
        Long memberNum = 0l;

        if(sessionDTO != null){
            memberNum = sessionDTO.getMemberNum();
        }

        List<Long> ordersDetailsNum = roomReviewRepository.writeableOrdersDetail(memberNum, roomNum);
        List<OrdersDetailDTO> ordersDetails = ordersDetailRepository.findAllById(ordersDetailsNum).stream()
                .map(OrdersDetailDTO::new)
                .collect(Collectors.toList());

        return ordersDetails;
    }

    void reviewSave(SessionDTO sessionDTO, RoomReviewCreateRequestDTO roomReviewCreateRequestDTO){
        Member member = memberRepository.findById(sessionDTO.getMemberNum()).orElseThrow(() -> new IllegalArgumentException(String.format("%d번 멤버가 없습니다.", sessionDTO.getMemberNum())));
        Room room = roomRepository.findById(roomReviewCreateRequestDTO.getRoomNum()).orElseThrow(() -> new IllegalArgumentException(String.format("%d번 객실 정보가 없습니다.", roomReviewCreateRequestDTO.getRoomNum())));
        room.calculateRating(roomReviewCreateRequestDTO.getRating());

        OrdersDetail ordersDetail = ordersDetailRepository.findById(roomReviewCreateRequestDTO.getOrdersDetailNum()).orElseThrow(() -> new IllegalArgumentException(String.format("%d번 주문정보가 없습니다.", roomReviewCreateRequestDTO.getOrdersDetailNum())));
        ordersDetail.writtenReview();

        Review review = Review.builder()
                .member(member)
                .room(room)
                .memberName(roomReviewCreateRequestDTO.getName())
                .reviewRating(roomReviewCreateRequestDTO.getRating().doubleValue())
                .reviewContent(roomReviewCreateRequestDTO.getContent())
                .reviewCreateDate(LocalDate.now())
                .reviewModifyDate(LocalDate.now())
                .build();

        roomReviewRepository.save(review);
    }

    @Override
    public Boolean isMember(SessionDTO sessionDTO) {
        return sessionDTO == null ? false : true;
    }

    @Override
    public Boolean isPaid(Long memberNum, Long roomNum) {
        Long result = roomReviewRepository.checkReserve(memberNum, roomNum).orElse(0L);

        return result == 0 ? false : true;
    }

    @Override
    public Boolean withinTwoWeeks(Long memberNum, Long roomNum) {
        Long result = roomReviewRepository.withinTwoWeeks(memberNum, roomNum).orElse(0L);

        return result == 0 ? false : true;
    }

    @Override
    public List<MyReviewDTO> getMemberReivew(Long memberNum) {

        List<MyReviewDTO> reviewList = roomReviewRepository.findAllByMember_MemberNum(memberNum).stream()
                .map(MyReviewDTO::new)
                .collect(Collectors.toList());

        return reviewList;
    }
}
