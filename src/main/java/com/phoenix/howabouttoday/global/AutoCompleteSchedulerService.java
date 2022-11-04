package com.phoenix.howabouttoday.global;


import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.payment.entity.OrdersDetail;
import com.phoenix.howabouttoday.payment.repository.OrdersDetailRepository;
import com.phoenix.howabouttoday.payment.repository.OrdersRepository;
import com.phoenix.howabouttoday.reserve.domain.Reservation.ReserveStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class AutoCompleteSchedulerService {

    private final OrdersRepository ordersRepository;
    private final OrdersDetailRepository ordersDetailRepository;


    /** 현재 상태는 이용 전이고, 오늘이 이용 시작일인 모든 주문 상세들을 이용 중으로 바꾼다. **/
    public void updateOrdersDetailToInUse(){
        List<OrdersDetail> ordersDetailList = ordersDetailRepository.findAllByReserveStatusIsAndReserveUseStartDateEquals(ReserveStatus.READY, LocalDate.now());
        ordersDetailList.forEach((ordersDetail) -> ordersDetail.changeToInUse());
    }

    /** 현재 상태는 이용 중이고, 오늘이 이용종료일인 모든 주문 상세들을 이용 완료로 바꾼다. **/
    public void updateOrdersDetailToComplete(){
        List<OrdersDetail> ordersDetailList = ordersDetailRepository.findAllByReserveStatusIsAndReserveUseEndDateEquals(ReserveStatus.IN_USE, LocalDate.now());
        ordersDetailList.forEach((ordersDetail) -> ordersDetail.changeToComplete());
    }

    /** 하나의 orders내에 있는 모든 주문들의 날짜를 비교해서 제일 먼저인 날짜가 오늘이라면 orders의 상태를 이용 중으로 변경 **/
    public void updateOrdersToInUse(){
        List<Orders> ordersList = ordersRepository.changeOrdersStatusToInUse();
        ordersList.forEach((orders) -> orders.changeStatusToInUse());
    }

    /** 하나의 orders내에 있는 모든 주문들의 날짜를 비교해서 제일 나중인 날짜가 오늘이라면 orders의 상태를 이용완료로 변경 **/
    public void updateOrdersToDone(){
        List<Orders> ordersList = ordersRepository.changeOrdersStatusToDone();
        ordersList.forEach((orders) -> orders.changeStatusToComplete());
    }
}
