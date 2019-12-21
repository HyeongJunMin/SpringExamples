package com.jpamaria.mariaexam.repository;


import com.jpamaria.mariaexam.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
  Member findByName(String name);
}
