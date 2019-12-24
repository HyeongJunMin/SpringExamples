package com.hjmin.jpaboard.controller;

import com.hjmin.jpaboard.domain.Member;
import com.hjmin.jpaboard.domain.member.Address;
import com.hjmin.jpaboard.domain.member.Email;
import com.hjmin.jpaboard.domain.member.MemberResponseDto;
import com.hjmin.jpaboard.repository.MemberRepository;
import com.hjmin.jpaboard.service.MemberManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/member")
@AllArgsConstructor
public class MemberRESTController {

  private final MemberManager memberManager;


  @RequestMapping(value = "/chkConnection", method = RequestMethod.POST)
  public String chkConnection() {
    log.info("connection check");
    return "ok";
  }

  @GetMapping(value = "/{id}")
  public MemberResponseDto getUser(@PathVariable Long id) {
    return new MemberResponseDto(memberManager.findById(id));
  }

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public String signupMember(Member mem) {
    log.info("sign up member post");

    log.info("mem : {}", mem.toString());

    return "1";
  }
}
