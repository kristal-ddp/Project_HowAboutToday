package com.phoenix.howabouttoday.payment.enumType;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Slf4j
public class CouponStatusConverter implements AttributeConverter<CouponStatus, String> {
    @Override
    public String convertToDatabaseColumn(CouponStatus accomCategory) {
        if(accomCategory == null) return null;
        return accomCategory.getValue();
    }
    @Override
    public CouponStatus convertToEntityAttribute(String dbData) {

        if(dbData == null) return null;

        try {
            return CouponStatus.fromCode(dbData);
        }
        catch (IllegalArgumentException e){
            log.error("failure to convert cause unexpected code [{}]", dbData, e);
        }
        return null;
    }
}
