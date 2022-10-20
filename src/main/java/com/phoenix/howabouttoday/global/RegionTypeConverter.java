package com.phoenix.howabouttoday.global;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Slf4j
public class RegionTypeConverter implements AttributeConverter<RegionType, String> {


    @Override
    public String convertToDatabaseColumn(RegionType regionType) {
        if(regionType == null) return null;
        return regionType.getValue();
    }

    @Override
    public RegionType convertToEntityAttribute(String dbData) {

        if(dbData == null) return null;

        try {
            return RegionType.fromCode(dbData);
        }
        catch (IllegalArgumentException e){
            log.error("failure to convert cause unexpected code [{}]", dbData, e);
        }

        return null;
    }
}
