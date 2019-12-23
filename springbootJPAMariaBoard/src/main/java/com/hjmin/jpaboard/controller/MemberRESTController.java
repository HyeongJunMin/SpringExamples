package com.hjmin.jpaboard.controller;

import com.hjmin.jpaboard.domain.Member;
import com.hjmin.jpaboard.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/member")
public class MemberRESTController {

  @Autowired
  private MemberRepository memberRepository;

  @RequestMapping(value = "/chkConnection", method = RequestMethod.POST)
  public String chkConnection() {
    log.info("connection check");
    return "200";
  }

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public String signupMember(@RequestParam String mem) {
    log.info("sign up member post");

    log.info("mem : {}", mem.toString());
    Member member = new Member();
    member.setName(mem);
    member.setPw("1234");
    memberRepository.save(member);

    return "1";
  }
}
