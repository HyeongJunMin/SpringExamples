package com.hjmin.jpaboard.service;

import com.hjmin.jpaboard.domain.Member;
import com.hjmin.jpaboard.domain.member.MemberResponseDto;
import com.hjmin.jpaboard.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemberManager {

  private final MemberRepository memberRepository;

  public Member signUp(Member mem) {
    return memberRepository.save(mem);
  }

  public Member findById(long id) {
    final Optional<Member> member = memberRepository.findById(id);
    return member.get();
  }

  public PageImpl<MemberResponseDto> findAll(Pageable pageable) {
    final Page<Member> page = memberRepository.findAll(pageable);
    final List<MemberResponseDto> content = convert(page);
    return new PageImpl<>(content, pageable, page.getTotalElements());
  }

  private List<MemberResponseDto> convert(Page<Member> members) {
    return members.getContent()
        .parallelStream()
        .map(MemberResponseDto::new)
        .collect(Collectors.toList());
  }
}
