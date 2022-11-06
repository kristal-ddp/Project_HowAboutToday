package com.phoenix.howabouttoday.global;

import com.phoenix.howabouttoday.accom.entity.Facility;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Slf4j
public class FacTypeConverter implements AttributeConverter<Facility, String> {


    @Override
    public String convertToDatabaseColumn(Facility facility) {
        if(facility == null) return null;
        return facility.getValue();
    }

    @Override
    public Facility convertToEntityAttribute(String dbData) {

        if(dbData == null) return null;

        try {
            return Facility.fromCode(dbData);
        }
        catch (IllegalArgumentException e){
            log.error("failure to convert cause unexpected code [{}]", dbData, e);
        }

        return null;
    }
}
