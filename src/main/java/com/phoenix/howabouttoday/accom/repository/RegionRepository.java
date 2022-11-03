package com.phoenix.howabouttoday.accom.repository;

import com.phoenix.howabouttoday.accom.dto.RegionDTO;
import com.phoenix.howabouttoday.accom.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {
    static List<RegionDTO> findByAll() {
        return null;
    }


    /** 지역 부모타입만 조회 **/
    List<Region> findAllByParentRegionIsNull();

    /** 지역 자식타입만 조회 **/
    List<Region> findAllByParentRegionIsNotNull();

}
