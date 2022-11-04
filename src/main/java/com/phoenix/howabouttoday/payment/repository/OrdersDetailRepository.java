package com.phoenix.howabouttoday.payment.repository;

import com.phoenix.howabouttoday.payment.entity.OrdersDetail;
import com.phoenix.howabouttoday.reserve.domain.Reservation.ReserveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Long> {



    List<OrdersDetail> findAllByReserveStatusIsAndReserveUseStartDateEquals(ReserveStatus reserveStatus, LocalDate localDate);
    List<OrdersDetail> findAllByReserveStatusIsAndReserveUseEndDateEquals(ReserveStatus reserveStatus, LocalDate localDate);


}
