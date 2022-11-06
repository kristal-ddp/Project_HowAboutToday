package com.phoenix.howabouttoday.room.controller;


import com.phoenix.howabouttoday.config.auth.LoginUser;
import com.phoenix.howabouttoday.member.Service.MemberService;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.member.dto.SessionDTO;
import com.phoenix.howabouttoday.payment.dto.CouponRequestDTO;
import com.phoenix.howabouttoday.payment.dto.CouponResponseDTO;
import com.phoenix.howabouttoday.payment.dto.RoomReviewCreateRequestDTO;
import com.phoenix.howabouttoday.payment.dto.RoomReviewCreateResponseDTO;
import com.phoenix.howabouttoday.payment.enumType.ReviewResponseCode;
import com.phoenix.howabouttoday.payment.service.CouponService;
import com.phoenix.howabouttoday.payment.service.OrdersService;
import com.phoenix.howabouttoday.room.dto.RoomReviewDTO;
import com.phoenix.howabouttoday.room.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/roomReview")
@RestController
public class RestReviewController {

    private final ReviewService reviewService;
    //더보기 방식을 위한 리스트
    @GetMapping("{roomNum}")
    public Slice<RoomReviewDTO> roomReviews(@PathVariable(required = false) Long roomNum,
                                            @PageableDefault(page = 0, size = 3, sort = "reviewNum", direction = Sort.Direction.DESC) Pageable pageable) {

        return reviewService.getRoomReviewList(pageable, roomNum);
    }

    @PostMapping("/save")
    public RoomReviewCreateResponseDTO postReviewSave(Model model, @LoginUser SessionDTO sessionDTO,@RequestBody RoomReviewCreateRequestDTO roomReviewCreateRequestDTO){
        System.out.println("실행");

//        1. 회원확인
//        2. 객실 예약완료 후 이용하고 난 뒤 14일 이내인가?
//        if(sessionDTO == null){
//            return new RoomReviewCreateResponseDTO(ReviewResponseCode.NOT_MEMBER);
//        }

        // 객실 이용 후 14일 이내라면 code
        return reviewService.isPossibleWrite(sessionDTO, roomReviewCreateRequestDTO);
    }

    @PatchMapping("/update/{reviewNum}")
    public String patchReviewUpdate(Model model, @LoginUser SessionDTO sessionDTO, @PathVariable(name = "reviewNum") String reviewNum){

        return null;
    }

    @DeleteMapping("/delete/{reviewNum}")
    public String postReviewDelete(Model model, @LoginUser SessionDTO sessionDTO, @PathVariable(name = "reviewNum") String reviewNum){

        return null;
    }
}