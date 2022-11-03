package com.phoenix.howabouttoday.accom.service;

import com.phoenix.howabouttoday.accom.entity.Region;

import java.util.List;

public interface RegionService {


    /** 부모 지역 조회 **/
    List<Region> findAllParent();

    /** 자식 지역 조회 **/
    List<Region> findAllChild();
}
