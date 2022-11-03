package com.phoenix.howabouttoday.payment.enumType;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Slf4j
public class ReviewStatusConverter implements AttributeConverter<ReviewStatus, String> {
    @Override
    public String convertToDatabaseColumn(ReviewStatus accomCategory) {
        if(accomCategory == null) return null;
        return accomCategory.getValue();
    }
    @Override
    public ReviewStatus convertToEntityAttribute(String dbData) {

        if(dbData == null) return null;

        try {
            return ReviewStatus.fromCode(dbData);
        }
        catch (IllegalArgumentException e){
            log.error("failure to convert cause unexpected code [{}]", dbData, e);
        }
        return null;
    }
}
