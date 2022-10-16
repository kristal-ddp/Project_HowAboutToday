package com.phoenix.howabouttoday.accom.repository;

import com.phoenix.howabouttoday.accom.dto.RegionDTO;
import com.phoenix.howabouttoday.accom.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {
    static List<RegionDTO> findByAll() {
        return null;
    }


}
