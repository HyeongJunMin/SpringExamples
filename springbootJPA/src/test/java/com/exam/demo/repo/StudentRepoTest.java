package com.exam.demo.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
public class StudentRepoTest {

  @Autowired
  StudentRepository studentRepository;

  @Test
  public void stdTest() {
    String amountOfStudent = studentRepository.getAmountOfStudent();
    System.out.println("amountOfStudent = " + amountOfStudent);
  }
}
