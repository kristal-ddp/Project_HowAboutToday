package com.phoenix.howabouttoday.accom.entity;

import com.phoenix.howabouttoday.accom.RegionType;
import com.phoenix.howabouttoday.accom.RegionTypeConverter;
import lombok.*;

import javax.persistence.Id;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Region {

  @Id
  @GeneratedValue
  private Long regionNum;

  @Column(length = 50)
  @Convert(converter = RegionTypeConverter.class)
  private RegionType region;

  private RegionType regionParentNum;

  @Builder
  public Region(RegionType region, RegionType regionParentNum){
    this.region = region;
    this.regionParentNum = regionParentNum;
  }
}
