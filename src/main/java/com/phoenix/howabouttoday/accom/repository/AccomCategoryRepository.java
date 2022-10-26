package com.phoenix.howabouttoday.accom.repository;


import com.phoenix.howabouttoday.accom.entity.AccomCategory;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccomCategoryRepository extends JpaRepository<AccomCategory,Long> {

   AccomCategory findByName(String name);

}
