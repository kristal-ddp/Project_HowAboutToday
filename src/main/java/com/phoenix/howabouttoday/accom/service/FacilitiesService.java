package com.phoenix.howabouttoday.accom.service;

import com.phoenix.howabouttoday.accom.entity.Facilities;
import com.phoenix.howabouttoday.accom.repository.FacilitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FacilitiesService {

    private final FacilitiesRepository facilitiesRepository;

    @Transactional
    public List<Facilities> getFacilitiesList() {
        return facilitiesRepository.findAll();
    }
}
