package com.phoenix.howabouttoday.room.repository;

import com.phoenix.howabouttoday.room.entity.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmenitiesRepository extends JpaRepository<Amenities,Long> {


}
