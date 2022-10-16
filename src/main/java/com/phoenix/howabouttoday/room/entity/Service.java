package com.phoenix.howabouttoday.room.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Service {

    @Id
    @GeneratedValue
    @Column
    private Long serviceNum;//서비스번호

    @Column
    private String serviceName;//서비스이름

}
