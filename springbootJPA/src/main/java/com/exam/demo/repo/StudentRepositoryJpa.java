package com.exam.demo.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public class StudentRepositoryJpa implements StudentRepository{

    @Override
    public String getAmountOfStudent() {
        return "됐다 연결이 됐다";
    }
}
