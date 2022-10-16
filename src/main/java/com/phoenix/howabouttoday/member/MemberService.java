package com.phoenix.howabouttoday.member;

import com.phoenix.howabouttoday.accom.RegionType;
import com.phoenix.howabouttoday.accom.entity.AccomImage;
import com.phoenix.howabouttoday.accom.entity.Accommodation;
import com.phoenix.howabouttoday.accom.repository.AccommodationRepository;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import com.phoenix.howabouttoday.payment.AccomCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class MemberService {


    private final MemberRepository memberRepository;
//    private final AccommodationImageRepository accommodationImageRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
//        this.accommodationImageRepository = accommodationImageRepository;
    }


}
