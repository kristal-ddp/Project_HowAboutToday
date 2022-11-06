package com.phoenix.howabouttoday.accom.service;

import com.phoenix.howabouttoday.accom.entity.Region;
import com.phoenix.howabouttoday.accom.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    /** 부모 지역 조회 **/
    @Override
    public List<Region> findAllParent() {
        return regionRepository.findAllByParentRegionIsNull();
    }

    /** 자식 지역 조회 **/
    @Override
    public List<Region> findAllChild() {
        return regionRepository.findAllByParentRegionIsNotNull();
    }

    /** regionNum에 해당하는 지역 조회 **/
    @Override
    public Region findByNum(Long regionNum){
        Region findRegion = regionRepository.findById(regionNum).orElseThrow(() -> new IllegalArgumentException("해당 지역은 존재하지 않습니다"));

        return findRegion;
    }
}
