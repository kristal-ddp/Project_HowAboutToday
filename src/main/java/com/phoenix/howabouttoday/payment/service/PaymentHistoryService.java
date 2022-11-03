package com.phoenix.howabouttoday.payment.service;

import com.phoenix.howabouttoday.payment.dto.OrdersDTO;
import com.phoenix.howabouttoday.payment.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class PaymentHistoryService {

    private final OrdersRepository ordersRepository;

    /** 멤버번호와 페이징정보를 가지고 원하는 page내용의 ordersDTO를 추출 **/
    public Page<OrdersDTO> pagingAllByMember(Pageable pageable, Long memberNum){

        List<OrdersDTO> ordersList =  ordersRepository.findAllByMember_MemberNum(memberNum)
                .stream()
                .map(OrdersDTO::new)
                .collect(Collectors.toList());

        Collections.sort(ordersList, Collections.reverseOrder());

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), ordersList.size());
        return new PageImpl<>(ordersList.subList(start, end), pageable, ordersList.size());
    }

    /** Orders to OrderDTO **/
    public OrdersDTO getOrdersDTO(Long ordersNum){
        return new OrdersDTO(ordersRepository.findById(ordersNum).orElseThrow(() -> new IllegalArgumentException("해당 주문 정보가 없습니다.")));
    }
}
