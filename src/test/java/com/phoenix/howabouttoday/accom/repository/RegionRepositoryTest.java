package com.phoenix.howabouttoday.accom.repository;

import com.phoenix.howabouttoday.accom.entity.Region;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RegionRepositoryTest {


    @Autowired
    private RegionRepository regionRepository;

    @Test
    public void 부모타입조회() throws Exception{

        List<Region> allByParentRegionIsNull = regionRepository.findAllByParentRegionIsNull();

        Assertions.assertThat(allByParentRegionIsNull.size()).isEqualTo(2);
    }


    @Test
    public void 자식타입조회() throws Exception{
        List<Region> allByParentRegionIsNotNull = regionRepository.findAllByParentRegionIsNotNull();

        List<Region> allByParentRegionIsNotNull1 = regionRepository.findAllByParentRegionIsNotNull();

        Assertions.assertThat(allByParentRegionIsNotNull1.size()).isEqualTo(6);

    }
}