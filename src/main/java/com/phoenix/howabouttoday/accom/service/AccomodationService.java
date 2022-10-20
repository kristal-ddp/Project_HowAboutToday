package com.phoenix.howabouttoday.accom.service;

import com.phoenix.howabouttoday.accom.dto.AccommodationDTO;
import com.phoenix.howabouttoday.accom.entity.AccomImage;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.entity.Region;
import com.phoenix.howabouttoday.accom.repository.AccommodationImageRepository;
import com.phoenix.howabouttoday.accom.repository.AccommodationRepository;
import com.phoenix.howabouttoday.accom.repository.RegionRepository;
import lombok.Builder;
import com.phoenix.howabouttoday.global.AccomCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
//    private final AccommodationImageRepository accommodationImageRepository;
    private final AccommodationImageRepository accommodationImageRepository;

    /*리스트 목록 조회*/
    public List<Accommodation> getAccommodationlist() {
        return accommodationRepository.findAll();
    }

    public List<AccomImage> getAccomImage() {return accommodationImageRepository.findAll(); }
    /*public void saveData(){
        accommodationRepository.save(createAccom());
    }
    public Accommodation createAccom() {

        Optional<Region> byId = regionRepository.findById(1L);//1L은 집에서 임의의 데이터를 입력할때 마다 다르기 때문에 확인 해줘야함.
        Region region = byId.get();
        log.info("Region",region.getRegionNum());

        Accommodation accommodation = Accommodation.builder()
                .accomName("보령(대천) 너울펜션")
                .accomTel("050350577805")
                .accomCategory(AccomCategory.PENSION)
                .region(region)
                .accomAddress("충청남도 보령시 해수욕장13길 10-20")
                .accomRating(4.4)
                .accomWishlistCount(110)
                .totalReviewNum(1103)
                .latitude(36.3196)
                .longitude(126.5092)
                .lowPrice(45000)
                .reserveRange(60)
                .build();

        AccomImage image = AccomImage.builder()
                .accomOriginFilename("image0.jpg")
                .accomSaveFilename("image0.jpg")
                .accommodation(accommodation)
                .build();

        return accommodation;
    }*/

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



}

