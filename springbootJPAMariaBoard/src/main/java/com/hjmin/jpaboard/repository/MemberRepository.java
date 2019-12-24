package com.hjmin.jpaboard.repository;

import com.hjmin.jpaboard.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
