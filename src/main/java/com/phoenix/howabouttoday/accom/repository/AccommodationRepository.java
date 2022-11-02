package com.phoenix.howabouttoday.accom.repository;


import com.phoenix.howabouttoday.accom.entity.Accommodation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Override
    <S extends Accommodation> S save(S entity);

    Accommodation findByAccomNum(Long number);

    List<Accommodation> findByAccomNameContaining(String keyword);

    /** 지역이 제외된 전체리스트 **/
    @EntityGraph(attributePaths = {"accomCategory","region","accommodationImage"})
    Slice<Accommodation>
    findByAccomCategory_NameAndLowPriceLessThanEqualAndLowPriceGreaterThanEqualAndAccomRatingLessThanEqualAndAccomNameContaining
            (String category_name,int maxPrice,int minPrice,
             Pageable pageable,Double accomRating,String keyword);

    /** 지역을 조회했을때 반환하는 전체리스트 **/
    @EntityGraph(attributePaths = {"accomCategory","region","accommodationImage"})
    Slice<Accommodation>
    findByRegion_RegionNumAndAccomCategory_NameAndLowPriceLessThanEqualAndLowPriceGreaterThanEqualAndAccomRatingLessThanEqualAndAccomNameContaining
    (Long regionNum,String category_name,int maxPrice,int minPrice,
     Pageable pageable,Double accomRating,String keyword);

    @EntityGraph(attributePaths = {"accomCategory","region","accommodationImage"})
    Slice<Accommodation> findByAccomCategory_Name(String category_name, Pageable pageable);



}


