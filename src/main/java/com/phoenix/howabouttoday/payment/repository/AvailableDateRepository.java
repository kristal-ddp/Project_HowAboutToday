
/* room이 가지고 있을 예약이 완료 된 날짜 repository */

package com.phoenix.howabouttoday.payment.repository;

import com.phoenix.howabouttoday.payment.entity.Orders;
import com.phoenix.howabouttoday.room.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {

//    List<AvailableDate> findByOneDayBetween(LocalDate startDate, LocalDate endDate);
//
//    void deleteAllByAvailableDateIdIn(List<Long> ids);

//    List<Orders> findAllByMember_MemberNum(Long memberId);

    void deleteAllByRoom_RoomNumAndOneDayBetween(Long aLong, LocalDate startDate, LocalDate endDate);

//    List<AvailableDate> deleteByOneDayBetween(LocalDate startDate, LocalDate endDate);
}


