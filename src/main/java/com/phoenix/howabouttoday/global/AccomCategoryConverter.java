//package com.phoenix.howabouttoday.global;
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//
//@Converter
//@Slf4j
//public class AccomCategoryConverter implements AttributeConverter<AccomCategory, String> {
//
//
//    @Override
//    public String convertToDatabaseColumn(AccomCategory accomCategory) {
//        if(accomCategory == null) return null;
//        return accomCategory.getValue();
//    }
//
//    @Override
//    public AccomCategory convertToEntityAttribute(String dbData) {
//
//        if(dbData == null) return null;
//
//        try {
//            return AccomCategory.fromCode(dbData);
//        }
//        catch (IllegalArgumentException e){
//            log.error("failure to convert cause unexpected code [{}]", dbData, e);
//        }
//
//        return null;
//    }
//}
