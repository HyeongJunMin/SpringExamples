package com.exam.restdocs.common;

import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

/**
 * RestDocs 설정 커스터마이징
 * 이 설정을 사용하기위해서는 test 클래스에서 @Import 추가
 */
@TestConfiguration
public class RestDocsConfiguration {

  //Pretty printing 프로세서를 활용해서 adoc 파일들 포맷 변경
  @Bean
  public RestDocsMockMvcConfigurationCustomizer restDocsMockMvcConfigurationCustomizer() {
    return configurer -> configurer.operationPreprocessors()
            .withRequestDefaults(prettyPrint())
            .withResponseDefaults(prettyPrint());

//    return new RestDocsMockMvcConfigurationCustomizer() {
//      @Override
//      public void customize(MockMvcRestDocumentationConfigurer configurer) {
//        configurer.operationPreprocessors()
//            .withRequestDefaults(prettyPrint())
//            .withResponseDefaults(prettyPrint());
//      }
//    }
  }
}
