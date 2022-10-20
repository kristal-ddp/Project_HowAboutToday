package com.phoenix.howabouttoday.payment.repository;

import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByMember_MemberNum(Long memberId);


}
