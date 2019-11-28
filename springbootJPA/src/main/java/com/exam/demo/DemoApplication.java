package com.exam.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;

@SpringBootApplication
//선언된 쿼리가 없으면 메소드 이름을 분석해서 쿼리 생성
//@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
//메소드 이름을 분석해서 쿼리 생성
//@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.CREATE)
//선언된 쿼리 사용
//@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.USE_DECLARED_QUERY)
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
