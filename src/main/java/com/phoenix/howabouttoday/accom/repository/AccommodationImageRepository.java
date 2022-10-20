package com.phoenix.howabouttoday.accom.repository;

import com.phoenix.howabouttoday.accom.entity.AccomImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AccommodationImageRepository extends JpaRepository<AccomImage, Long> {

    @Override
    <S extends AccomImage> S save(S entity);

    @Override
    List<AccomImage> findAll();
    AccomImage findByAccomNum(Long number);


}
