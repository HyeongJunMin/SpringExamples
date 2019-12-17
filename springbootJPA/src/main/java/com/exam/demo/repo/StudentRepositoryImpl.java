package com.exam.demo.repo;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

  @Override
  public String getAmountOfStudent() {
    return "Student Impl!";
  }
}
