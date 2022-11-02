package com.phoenix.howabouttoday.accom.entity;

import com.phoenix.howabouttoday.global.RegionType;
import com.phoenix.howabouttoday.global.RegionTypeConverter;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class Region {

  @Id
  @GeneratedValue
  private Long regionNum;

  @Column(length = 50)
  @Convert(converter = RegionTypeConverter.class)
  private RegionType region;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "region_parent_num",referencedColumnName = "regionNum")
  private Region parentRegion;

  @OneToMany(mappedBy = "parentRegion", cascade = CascadeType.ALL)
  private List<Region> childRegions = new ArrayList<>();
//
//  //양방향 매핑을 위해 추가
//  @JsonIgnore
//  @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
//  private List<Accommodation> accommodation = new ArrayList<>();    //이미지 fk를 위한 매핑


}
