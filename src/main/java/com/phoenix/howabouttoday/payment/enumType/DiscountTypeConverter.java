package com.phoenix.howabouttoday.payment.enumType;//package com.phoenix.howabouttoday.global;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Slf4j
public class DiscountTypeConverter implements AttributeConverter<DiscountType, String> {


    @Override
    public String convertToDatabaseColumn(DiscountType accomCategory) {
        if(accomCategory == null) return null;
        return accomCategory.getValue();
    }

    @Override
    public DiscountType convertToEntityAttribute(String dbData) {

        if(dbData == null) return null;

        try {
            return DiscountType.fromCode(dbData);
        }
        catch (IllegalArgumentException e){
            log.error("failure to convert cause unexpected code [{}]", dbData, e);
        }

        return null;
    }
}
