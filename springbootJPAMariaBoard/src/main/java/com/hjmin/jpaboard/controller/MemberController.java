package com.hjmin.jpaboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class MemberController {

  @RequestMapping(value = "/member/chk")
  public void chkConnection() {
    log.info("chk connection ok");
  }

  @RequestMapping(value = "/member/chk2")
  public void chkConnection2() {
    log.info("chk connection ok2");
  }
}
