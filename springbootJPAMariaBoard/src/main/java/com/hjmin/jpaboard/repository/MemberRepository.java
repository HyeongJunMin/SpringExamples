package com.hjmin.jpaboard.repository;

import com.hjmin.jpaboard.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
