package com.hjmin.jpaboard.controller;

import com.hjmin.jpaboard.domain.Member;
import com.hjmin.jpaboard.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/member")
public class MemberRESTController {

  @Autowired
  private MemberRepository memberRepository;

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public String signupMember(Member mem) {
    log.info("sign up member post");

    log.info("mem : {}", mem.toString());
    memberRepository.save(mem);

    return "1";
  }
}
