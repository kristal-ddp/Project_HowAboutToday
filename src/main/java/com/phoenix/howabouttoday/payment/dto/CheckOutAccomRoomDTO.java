/**
 * 결제창에서 객실과 룸 정보를 전달하는 dto
 *
 */

package com.phoenix.howabouttoday.payment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckOutAccomRoomDTO {

    private String accomName;
    private String roomName;
    private String checkIn;
    private String checkOut;
    private String region;
    private Integer night;
    private Integer guest;
    private String service;
    private Integer price;
}


