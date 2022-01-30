package com.example.querydsl.repository;

import com.example.querydsl.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserRepository extends GenericRepository {

  public UserRepository(EntityManager em) {
    super(em, User.class);
  }

}
