package com.phoenix.howabouttoday.room.repository;

import com.phoenix.howabouttoday.room.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomReviewRepository extends JpaRepository<Review,Long> {
//      이건 내가 생각을 잘못해서 orders와 조인한 쿼리, orderDetail이 membernum을 가지고 있어서 바로 두개만 조인하면 된다.
//    @Query(value = "select count(room_num) from reservation where orders_num in(select o.orders_num from member m inner join orders o on m.member_num = o.member_num and m.member_num = :#{#memberNum})" +
//            "and room_num = :#{#roomNum} and reserve_use_end_date <= now() and reserve_use_end_date > date_add(now(), INTERVAL -14 DAY) and is_review_writed = '작성 전' group by room_num", nativeQuery = true)
//    Long selectByReviewCheck(@Param(value = "memberNum") Long memberNum, @Param(value = "roomNum") Long roomNum);

    @Query(value = "select count(room_num) from member m inner join reservation r where r.reserve_type = 'orderDetail' and m.member_num = r.member_num and m.member_num = :#{#memberNum} " +
            "and room_num = :#{#roomNum} and reserve_use_end_date <= now() and reserve_use_end_date > date_add(now(), INTERVAL -14 DAY) and is_review_writed = '작성 전' group by room_num;", nativeQuery = true)
    Optional<Long> withinTwoWeeks(@Param(value = "memberNum") Long memberNum, @Param(value = "roomNum") Long roomNum);

    @Query(value = "select r.reserve_num from member m inner join reservation r where r.reserve_type = 'orderDetail' and m.member_num = r.member_num and m.member_num = :#{#memberNum} " +
            "and room_num = :#{#roomNum} and reserve_use_end_date <= now() and reserve_use_end_date > date_add(now(), INTERVAL -14 DAY) and is_review_writed = '작성 전' group by room_num;", nativeQuery = true)
    List<Long> writeableOrdersDetail(@Param(value = "memberNum") Long memberNum, @Param(value = "roomNum") Long roomNum);

    @Query(value = "select count(m.member_num) from member m inner join reservation r where m.member_num = r.member_num and r.reserve_type = 'orderDetail' and m.member_num = :#{#memberNum} and r.room_num = :#{#roomNum}", nativeQuery = true)
    Optional<Long> checkReserve(@Param(value = "memberNum") Long memberNum, @Param(value = "roomNum") Long roomNum);

    List<Review> findAllByRoom_RoomNum(Long roomNum);

    List<Review> findAllByMember_MemberNum(Long memberId);


//
//    // SQL 객체 파라미터 쿼리
//    @Query(value = "select snack_id, name, price from snack where snack_id > :#{#paramSnack.id}", nativeQuery = true)
//    public List<Snack> selectSQLById3(@Param(value = "paramSnack") Snack snack);
}
