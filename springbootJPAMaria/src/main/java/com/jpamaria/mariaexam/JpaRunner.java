package com.jpamaria.mariaexam;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Slf4j
@Component
@Transactional
public class JpaRunner implements ApplicationRunner {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    Session session = entityManager.unwrap(Session.class);

  }
}
