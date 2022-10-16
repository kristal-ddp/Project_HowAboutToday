package com.phoenix.howabouttoday.reserve.controller;

import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.payment.testDriver.CartDto_1;
import com.phoenix.howabouttoday.payment.testDriver.CartService;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.room.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class ReserveController {

    private final CartService cartService;


    @GetMapping("hotel-booking")
    public String getHotelBooking(/*@ModelAttribute CartDto_1 cartDto1*/Model model){
        System.out.println("오지?");
        cartService.saveData();

        Cart cart = cartService.getAllData().get(0);


        CartDto_1 cartDto1 = CartDto_1.builder()
                .reserveNum(cart.getReserveNum())
                .member(cart.getMember())
                .accommodation(cart.getAccommodation())
                .room(cart.getRoom())
                .reserveUseStartDate(cart.getReserveUseStartDate())
                .reserveUseEndDate(cart.getReserveUseEndDate())
                .reservePrice(cart.getReservePrice())
                .reserveAdultCount(cart.getReserveAdultCount())
                .build();

        model.addAttribute("cart", cartDto1);

        return "reserve/checkout";
    }
    @PostMapping("hotel-booking")
    public String postHotelBooking(){


        return "reserve/checkout";
    }

    @GetMapping("checkout")
    public String getCheckout(){
        return "reserve/checkout";
    }
    @PostMapping("checkout")
    public String postCheckout(){
        return "reserve/checkout";
    }

    @GetMapping("payment-received")
    public String getPaymentReceived(){
        return "reserve/payment-received";
    }
    @PostMapping("payment-received")
    public String postPaymentReceived(){
        return "reserve/payment-received";
    }

    @GetMapping("payment-complete")
    public String getPaymentComplete(){
        return "reserve/payment-complete";
    }
    @PostMapping("payment-complete")
    public String postPaymentComplete(){
        return "reserve/payment-complete";
    }

}
