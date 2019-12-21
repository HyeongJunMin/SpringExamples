package com.jpamaria.mariaexam;

import com.jpamaria.mariaexam.domain.Member;
import lombok.extern.slf4j.Slf4j;
import com.jpamaria.mariaexam.domain.ServerInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Slf4j
@SpringBootTest
class MariaexamApplicationTests {

  @Autowired
  private EntityManagerFactory entityManagerFactory;

  @Test
  @Rollback(false)
  void contextLoads() {


  }

}
