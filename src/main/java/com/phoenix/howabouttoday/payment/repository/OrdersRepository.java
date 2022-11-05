package com.phoenix.howabouttoday.payment.repository;

import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByMember_MemberNum(Long memberId);

//    List<Orders> findAllByMember_MemberNum(Long memberId);

    Optional<Orders> findByMerchantId(String merchantId);

    @Override
    Page<Orders> findAll(Pageable pageable);

//  전체 orders를 순회하면서
//  orders의 orders_status가 결제완료인
//  ordersDetail의 reserve_type = 'orderDetail 이고 reserve_status = 'READY' 인(이건 굳이 안해도 되긴 할거 같다. 이유는 orders_status가 결제완료라면 reserve_status는 무조건 READY여야 한다.)
//  모든 ordersDetail의 reserve_use_start_date를 비교해서 제일 빠른 날짜를 가져오고, 그게 특정날짜(오늘) 이라면 orders를 반환한다.
//    @Query(value = "select o.* from orders o inner join reservation r on o.orders_num = r.orders_num and orders_status = '결제완료' and r.reserve_type = 'orderDetail'" +
//            " and reserve_status = 'READY' group by o.orders_num having min(r.reserve_use_start_date) = Date(now())", nativeQuery = true)
    @Query(value = "select o.* from orders o inner join reservation r on o.orders_num = r.orders_num and orders_status = '결제완료' and r.reserve_type = 'orderDetail'" +
            " and reserve_status = 'READY' group by o.orders_num having min(r.reserve_use_start_date) = Date(now())", nativeQuery = true)
    List<Orders> changeOrdersStatusToInUse();

    @Query(value = "select o.* from orders o inner join reservation r on o.orders_num = r.orders_num and orders_status = '이용 중' and r.reserve_type = 'orderDetail'" +
            " group by o.orders_num having max(r.reserve_use_end_date) = Date(now())", nativeQuery = true)
    List<Orders> changeOrdersStatusToDone();

}
