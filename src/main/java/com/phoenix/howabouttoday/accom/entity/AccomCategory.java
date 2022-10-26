package com.phoenix.howabouttoday.accom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class AccomCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accomCategoryNum;

    @Column(nullable = false, unique = true)
    private String name; // 카테고리 영어 이름

    @Column(nullable = false, unique = true)
    private String viewName; // 화면에 보여줄 한글 이름
}

