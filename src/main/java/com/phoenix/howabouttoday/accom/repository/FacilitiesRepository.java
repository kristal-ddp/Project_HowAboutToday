package com.phoenix.howabouttoday.accom.repository;

import com.phoenix.howabouttoday.accom.entity.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilitiesRepository extends JpaRepository<Facilities, Long> {

    @Override
    List<Facilities> findAll();

}
