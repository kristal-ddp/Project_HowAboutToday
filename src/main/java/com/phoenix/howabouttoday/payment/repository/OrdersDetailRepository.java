package com.phoenix.howabouttoday.payment.repository;

import com.phoenix.howabouttoday.payment.entity.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Long> {
}
