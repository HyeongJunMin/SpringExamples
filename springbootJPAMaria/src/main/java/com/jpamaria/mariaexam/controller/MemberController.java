package com.jpamaria.mariaexam.controller;

import com.jpamaria.mariaexam.domain.Member;
import com.jpamaria.mariaexam.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Slf4j
@RestController
public class MemberController {

  @Autowired
  private MemberRepository memberRepository;

  @RequestMapping(value = "/check/connection")
  public void checkConnection() {
    log.info("conn ok");
    Member mem = new Member();
    mem.setName("min");

    log.info("mem : {} ", mem.toString());

    memberRepository.save(mem);
    log.info("save member!");

  }

}
