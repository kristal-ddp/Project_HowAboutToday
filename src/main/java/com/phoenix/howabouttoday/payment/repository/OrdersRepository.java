package com.phoenix.howabouttoday.payment.repository;

import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByMember_MemberNum(Long memberId);

    Optional<Orders> findByMerchantId(String merchantId);

    @Override
    Page<Orders> findAll(Pageable pageable);

}
