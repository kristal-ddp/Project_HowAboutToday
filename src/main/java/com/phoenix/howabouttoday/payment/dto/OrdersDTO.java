
/* orders 엔티티와 매핑되는 사이트 */

package com.phoenix.howabouttoday.payment.dto;
import com.phoenix.howabouttoday.global.OrdersStatus;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.payment.entity.Orders;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Getter
public class OrdersDTO implements Comparable<OrdersDTO> {

    private Long ordersNum;         //pk값
    private String ordersDate;
    private Integer ordersPrice;
    private Integer actualPayment;
    private Integer discountValue;
    private String ordersTel;
    private String ordersName;
    private String ordersType;
    private String ordersStatus;
    private String ordersMerchantId;
    private List<OrdersDetailDTO> ordersDetailDTOList;

    public OrdersDTO(Orders orders) {
        this.ordersNum = orders.getOrdersNum();
        this.ordersTel = orders.getOrdersTel();
        this.ordersName = orders.getOrdersName();
        this.ordersDate = orders.getOrdersDate().toLocalDate().toString();
        this.ordersPrice = orders.getOrdersPrice();
        this.actualPayment = orders.getDiscountedPrice();
        this.discountValue = orders.getDiscountValue();
        this.ordersType = orders.getOrdersType();
        this.ordersStatus = orders.getOrdersStatus().getValue();
        this.ordersMerchantId = orders.getMerchantId();
        this.ordersDetailDTOList = orders.getReservation().stream().map(OrdersDetailDTO::new).collect(Collectors.toList());
    }

    public OrdersDTO() {
    }

    @Override
    public int compareTo(OrdersDTO ordersDTO) {

        if (this.getOrdersNum() > ordersDTO.getOrdersNum()) {
            return 1;
        } else if (this.getOrdersNum() < ordersDTO.getOrdersNum()) {
            return -1;
        }
        return 0;
    }

    public Boolean isCanceled(){
        return getOrdersStatus() == OrdersStatus.PAYMENT_CANCEL.getValue();
    }
}
