package com.phoenix.howabouttoday.accom.service;

import com.phoenix.howabouttoday.accom.RegionType;
import com.phoenix.howabouttoday.accom.entity.AccomImage;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.entity.Region;
import com.phoenix.howabouttoday.accom.repository.AccommodationRepository;
import com.phoenix.howabouttoday.payment.AccomCategory;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class AccomodationService {


    private final AccommodationRepository accommodationRepository;
//    private final AccommodationImageRepository accommodationImageRepository;

    @Autowired
    public AccomodationService(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
//        this.accommodationImageRepository = accommodationImageRepository;
    }

    @Transactional
    public void createAccom() {


        Region region  = Region.builder()
                .region(RegionType.SEOUL)
                .regionParentNum(RegionType.SEOUL)
                .build();

        Accommodation newMember = Accommodation.builder()
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
                .accommodation(newMember)
                .build();

        newMember.getAccommodationImage().add(image);


//        accommodationImageRepository.save(image);
        accommodationRepository.save(newMember);
    }

    /*
    리스트 목록 조회
     */
    @Transactional
    public List<Accommodation> getAccommodationlist() {

        return accommodationRepository.findAll();
    }

}

