package com.example.querydsl.controller;

import com.example.querydsl.domain.User;
import com.example.querydsl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestController {

  private final UserRepository userRepository;

  @GetMapping("/test")
  public String test(Model model) {
    log.info("test");
    List all = userRepository.findAll();
    model.addAttribute("result", all);
    return "index";
  }

  @GetMapping("/test/createUser")
  public String testCreateUser(@RequestParam(required = false) String name, Model model) {
    User user = new User(name);
    userRepository.save(user);
    model.addAttribute("result", "user saved");
    return "index";
  }

}
