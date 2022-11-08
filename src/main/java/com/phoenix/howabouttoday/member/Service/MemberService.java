package com.phoenix.howabouttoday.member.Service;

//import com.phoenix.howabouttoday.global.ImageUtil;
import com.phoenix.howabouttoday.global.MailUtil;
import com.phoenix.howabouttoday.member.dto.MailDTO;
import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.member.entity.Member;
import com.phoenix.howabouttoday.member.entity.Role;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import com.phoenix.howabouttoday.payment.CouponFactory;
import com.phoenix.howabouttoday.payment.entity.Coupon;
import com.phoenix.howabouttoday.payment.entity.CouponRules;
import com.phoenix.howabouttoday.payment.enumType.CouponNumber;
import com.phoenix.howabouttoday.payment.enumType.CouponStatus;
import com.phoenix.howabouttoday.payment.enumType.DiscountType;
import com.phoenix.howabouttoday.payment.repository.CouponRepository;
import com.phoenix.howabouttoday.payment.repository.CouponRulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final BCryptPasswordEncoder encoder;
    private final CouponFactory couponFactory;

    @Transactional
    public Long join(MemberDTO DTO) {
        DTO.setPwd(encoder.encode(DTO.getPwd()));

        Member member = memberRepository.save(DTO.toEntity());
        couponFactory.create(CouponNumber.FIRST, member);

        return member.getMemberNum();
    }
//    원래 join 메서드
//    @Transactional
//    public Long join(MemberDTO DTO) {
//        DTO.setPwd(encoder.encode(DTO.getPwd()));
//
//        return memberRepository.save(DTO.toEntity()).getMemberNum();
//    }


    /* 회원가입 시, 유효성 체크 */
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        //유효성 검사에 실패한 필드 목록을 받음
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    /* 회원수정 (dirty checking) */
    @Transactional
    public void modify(MemberDTO memberDTO) {
        Member member = memberRepository.findById(memberDTO.getMemberNum()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));


        String encPassword = encoder.encode(memberDTO.getPwd());
        member.modify(encPassword,memberDTO.getNickname(),memberDTO.getMemberTel());

    }

    // 비밀번호 찾기
    public Member pwdFind(String email) throws Exception {

        // 회원정보 불러오기
        Member member = memberRepository.findByEmail(email).orElse(null);

        // 이메일 전송
        if(member!=null) {

            // 임시 비밀번호 생성
            String tempPwd = UUID.randomUUID().toString().replace("-", ""); // - 제거
            tempPwd = tempPwd.substring(0, 10); // 10자리로 생성

            System.out.print("임시 비밀번호 : " + tempPwd);
            member.findPwd(tempPwd);

            // 이메일 전송
            MailUtil mail = new MailUtil();
            mail.sendMail(member);

            // 암호화된 임시 비밀번호 저장
            member.findPwd(passwordEncoder.encode(member.getPwd()));

            memberRepository.save(member);
        }
        return member;

    }

    // 이메일과 비밀번호 일치 여부 체크
    // 회원탈퇴
    public Member checkPwd(String email, String pwd) {
        return memberRepository.findByEmail(email) // email로 회원을 조회하고
                .filter(m -> this.passwordEncoder.matches(pwd, m.getPwd())) // 입력한 pwd와 암호화된 pwd(m.getPwd)가 같으면, 회원을 반환하고
                .orElse(null); // 다르면 null을 반환한다
    }

    // 회원 탈퇴
    @Transactional
    public void withdraw(String email) {

        Member member = memberRepository.findByEmail(email).orElse(null);
        memberRepository.delete(member);
    }



    public MemberDTO getSessionUser(Long memberNum){
        Member member = memberRepository.findById(memberNum).orElseThrow(() -> new IllegalArgumentException(String.format("%d번 멤버 정보가 없습니다.", memberNum)));

        return MemberDTO.builder()
                .memberNum(member.getMemberNum())
                .email(member.getEmail())
                .pwd(member.getPwd())
                .nickname(member.getNickname())
                .memberTel(member.getMemberTel())
                .role(member.getRole())
                .build();
    }

    public MemberDTO getAuthUser(String email){

        Member member = memberRepository.findByEmail(email).get();

        return MemberDTO.builder()
                .memberNum(member.getMemberNum())
                .email(member.getEmail())
                .pwd(member.getPwd())
                .nickname(member.getNickname())
                .memberTel(member.getMemberTel())
                .role(member.getRole())
                .build();
    }

    public MemberDTO getCustomer(Long memberNum) throws UsernameNotFoundException {

        Member member = memberRepository.findById(memberNum).get();

        MemberDTO customer = MemberDTO.builder()
                .memberNum(member.getMemberNum())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .memberTel(member.getMemberTel())
                .role(member.getRole())
                .build();

        return customer;
    }


}


