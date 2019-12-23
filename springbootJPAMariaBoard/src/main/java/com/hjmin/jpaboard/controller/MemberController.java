package com.hjmin.jpaboard.controller;

import com.hjmin.jpaboard.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

  @RequestMapping(value = "/login")
  public String showLoginPage() {
    log.info("show login page");

    return "/member/login";
  }

  @RequestMapping(value = "/signup")
  public String showSignUpPage() {
    log.info("show signup page");

    return "/member/signup";
  }
}
