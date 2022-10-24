package com.phoenix.howabouttoday.accom.repository;


import com.phoenix.howabouttoday.accom.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Override
    <S extends Accommodation> S save(S entity);

    Accommodation findByAccomNum(Long number);

    @Override
    List<Accommodation> findAll();

    List<Accommodation> findByAccomNameContaining(String keyword);

    Optional<Accommodation> findById(Long accomNum);
}


