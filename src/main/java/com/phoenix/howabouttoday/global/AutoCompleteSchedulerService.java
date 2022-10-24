package com.phoenix.howabouttoday.global;


import com.phoenix.howabouttoday.payment.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AutoCompleteSchedulerService {

    OrdersRepository ordersRepository;


    public void updateOrdersState(){

    }


}
