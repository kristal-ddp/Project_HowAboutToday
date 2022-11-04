
/**
 * 결제가 완료되면 cart의 내용을 저장할 테이블
 * 사실 카트와 다른 부분은 거의 없지만, dType을 사용하려면 이렇게 사용해야한다.
 */


package com.phoenix.howabouttoday.payment.entity;

import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.payment.enumType.ReviewStatus;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Cart;
import com.phoenix.howabouttoday.reserve.domain.Reservation.Reservation;
import com.phoenix.howabouttoday.reserve.domain.Reservation.ReserveStatus;
import com.phoenix.howabouttoday.room.entity.Room;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@DiscriminatorValue("orderDetail")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
public class OrdersDetail extends Reservation {

    public void writtenReview(){
        this.isReviewWrited = ReviewStatus.POST_WRITE;
    }

}
