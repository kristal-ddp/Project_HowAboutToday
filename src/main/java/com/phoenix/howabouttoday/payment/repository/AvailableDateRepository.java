
/* room이 가지고 있을 예약이 완료 된 날짜 repository */

package com.phoenix.howabouttoday.payment.repository;

import com.phoenix.howabouttoday.room.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {
}
