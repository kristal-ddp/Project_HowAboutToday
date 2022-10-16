package com.phoenix.howabouttoday.reserve.domain;

import com.phoenix.howabouttoday.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
