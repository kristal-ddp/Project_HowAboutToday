package com.phoenix.howabouttoday.payment.service;

import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MemberServiceCopy implements UserDetailsService {


    MemberRepository memberRepository;

    public MemberServiceCopy(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDTO getCustomer(Long memberNum) throws UsernameNotFoundException{

        Member member = memberRepository.findById(memberNum).get();

        MemberDTO customer = MemberDTO.builder()
                .num(member.getMemberNum())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .memberTel(member.getMemberTel())
                .memberCode(member.getMemberCode())
                .build();

        return customer;
    }

    public Member getMember(Long memberNum) throws UsernameNotFoundException{
        return memberRepository.findById(memberNum).get();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("실행?");
        return null;
    }
}
