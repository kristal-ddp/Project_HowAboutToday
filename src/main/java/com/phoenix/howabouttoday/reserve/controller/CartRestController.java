package com.phoenix.howabouttoday.reserve.controller;

import com.phoenix.howabouttoday.reserve.service.CartService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/cart")
@RequiredArgsConstructor
public class CartRestController {

    private final CartService cartService;


    /** 장바구니 저장 **/


    /** 장바구니 페이지에서 특정 객실 삭제 **/
    @DeleteMapping("/{cartNum}")
    public ResponseEntity deleteByNum(@PathVariable Long cartNum){

        System.out.println("cartNum = " + cartNum);
        cartService.deleteByNum(cartNum);

        return new ResponseEntity(HttpStatus.OK);
    }
}
