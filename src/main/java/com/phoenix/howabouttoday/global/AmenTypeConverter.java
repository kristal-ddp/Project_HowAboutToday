package com.phoenix.howabouttoday.global;

import com.phoenix.howabouttoday.accom.entity.Facility;
import com.phoenix.howabouttoday.room.entity.AmenitiesNames;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Slf4j
public class AmenTypeConverter implements AttributeConverter<AmenitiesNames, String> {


    @Override
    public String convertToDatabaseColumn(AmenitiesNames amenitiesNames) {
        if(amenitiesNames == null) return null;
        return amenitiesNames.getValue();
    }

    @Override
    public AmenitiesNames convertToEntityAttribute(String dbData) {

        if(dbData == null) return null;

        try {
            return AmenitiesNames.fromCode(dbData);
        }
        catch (IllegalArgumentException e){
            log.error("failure to convert cause unexpected code [{}]", dbData, e);
        }

        return null;
    }
}
