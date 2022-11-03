package com.phoenix.howabouttoday.global;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Slf4j
public class OrdersStatusConverter implements AttributeConverter<OrdersStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrdersStatus accomCategory) {
        if(accomCategory == null) return null;
        return accomCategory.getValue();
    }
    @Override
    public OrdersStatus convertToEntityAttribute(String dbData) {

        if(dbData == null) return null;

        try {
            return OrdersStatus.fromCode(dbData);
        }
        catch (IllegalArgumentException e){
            log.error("failure to convert cause unexpected code [{}]", dbData, e);
        }

        return null;
    }
}
