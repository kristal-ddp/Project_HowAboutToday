package com.phoenix.howabouttoday.accom.entity;


import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AccomImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accomNum;

    @Column
    private String accomOriginFilename;

    @Column
    private String accomSaveFilename;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="accommodation_accomNum", referencedColumnName = "accomNum")
    private Accommodation accommodation;

    @Builder
    public AccomImage(String accomOriginFilename, String accomSaveFilename, Accommodation accommodation) {
        this.accomOriginFilename = accomOriginFilename;
        this.accomSaveFilename = accomSaveFilename;
        this.accommodation = accommodation;
    }
}


