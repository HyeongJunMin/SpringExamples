package com.exam.restdocs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

  @RequestMapping(value = "/chk")
  public String reqCheck() {
    log.info("welcome connection ok");
    return "welcome";
  }
}
