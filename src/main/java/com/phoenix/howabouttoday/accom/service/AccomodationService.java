package com.phoenix.howabouttoday.accom.service;


import com.phoenix.howabouttoday.accom.dto.AccomDto;
import com.phoenix.howabouttoday.accom.dto.AccommodationDTO;
import com.phoenix.howabouttoday.accom.entity.AccomImage;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.entity.Region;
import com.phoenix.howabouttoday.accom.repository.AccommodationImageRepository;
import com.phoenix.howabouttoday.accom.repository.AccommodationRepository;
import com.phoenix.howabouttoday.accom.repository.RegionRepository;
import lombok.Builder;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccomodationService {
    private final AccommodationRepository accommodationRepository;

    private final RegionRepository regionRepository;
    private final AccommodationImageRepository accommodationImageRepository;

    public Slice<AccomDto.ResponsePageDto> getAccomPageList(Pageable pageable,String category_name) {

        Slice<Accommodation> page = accommodationRepository.findByAccomCategory_Name(category_name,pageable);

        Slice<AccomDto.ResponsePageDto> accomPageList = page.map(accom -> new AccomDto.ResponsePageDto(accom));

        return accomPageList;
    }


    /*리스트 목록 조회*/
    public List<Accommodation> getAccommodationlist() {
        return accommodationRepository.findAll();
    }

    @Transactional
    public List<Accommodation> searchResults(String keyword) {
        List<Accommodation> accommodations = accommodationRepository.findByAccomNameContaining(keyword);
        List<AccommodationDTO> accomDtoList = new ArrayList<>();

        if (accommodations.isEmpty()) return accommodations;

        for (Accommodation accommodation : accommodations) {
            accomDtoList.add(this.convertEntityToDto(accommodation));
        }
        return accommodations;
    }

    public AccommodationDTO convertEntityToDto(Accommodation accommodation) {

        return AccommodationDTO.builder()
                .accomName(accommodation.getAccomName())
                .accomTel(accommodation.getAccomTel())
                .accomAddress(accommodation.getAccomAddress())
                .accomRating(accommodation.getAccomRating())
                .accomWishListCount(accommodation.getAccomWishlistCount())
                .totalreviewNum(accommodation.getTotalReviewNum())
                .latitude(accommodation.getLatitude())
                .longitude(accommodation.getLongitude())
                .lowPrice(accommodation.getLowPrice())
                .reserveRange(accommodation.getReserveRange())
                .build();

    }

    @Transactional
    public Accommodation findAccom(Long accomNum) {

        Optional<Accommodation> findAccom = accommodationRepository.findById(accomNum);
        Accommodation accommodation = findAccom.orElseThrow(() ->
                new IllegalArgumentException("해당 숙소가 존재하지 않습니다"));

        return accommodation;
    }

//    public List<Accommodation> searchAccommodation() {
//        List<Accommodation> accommodations = accommodationRepository.getAccommodationByNum();
//        return accommodations;
//    }

}

