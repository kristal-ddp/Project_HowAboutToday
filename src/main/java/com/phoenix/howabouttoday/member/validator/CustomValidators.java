package com.phoenix.howabouttoday.member.validator;

import com.phoenix.howabouttoday.member.dto.MemberDTO;
import com.phoenix.howabouttoday.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * 중복 확인 유효성 검증을 위한 커스텀 Validator 클래스
 */


@RequiredArgsConstructor
@Component
public class CustomValidators {


    @RequiredArgsConstructor
    @Component
    public static class EmailValidator extends AbstractValidator<MemberDTO> {
        private final MemberRepository memberRepository;

        @Override
        protected void doValidate(MemberDTO memberJoinDTO, Errors errors) {
            if (memberRepository.existsByEmail(memberJoinDTO.toEntity().getEmail())) {
                errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
            }
        }
    }
}