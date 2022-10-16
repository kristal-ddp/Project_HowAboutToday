package com.phoenix.howabouttoday.accom.service;

import com.phoenix.howabouttoday.accom.dto.RegionDTO;
import com.phoenix.howabouttoday.accom.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    @Transactional
    public List<RegionDTO> findByAll(){
        return RegionRepository.findByAll();
    }

}
