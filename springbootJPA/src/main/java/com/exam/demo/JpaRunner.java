package com.exam.demo;

import com.exam.demo.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Account account = new Account();
    account.setUsername("user1");
    account.setPassword("user1");

    log.info("account.getUsername() = " + account.getUsername());

    Session session = entityManager.unwrap(Session.class);
    session.save(account);
//        Account account1 = session.find(Account.class, account);
//        log.info(account1.getUsername());

  }
}
