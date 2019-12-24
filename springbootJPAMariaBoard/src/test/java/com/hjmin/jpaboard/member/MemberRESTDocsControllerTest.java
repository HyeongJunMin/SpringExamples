package com.hjmin.jpaboard.member;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRESTDocsControllerTest {

  //테스트 프레임워크 설정
  @Rule
  public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
  @Autowired
  private WebApplicationContext context;
  private MockMvc mockMvc;
  private RestDocumentationResultHandler document;

  //RestDocumentationResultHandler, MockMvc 객체 생성
  @Before
  public void setUp() {
    this.document = document(
        "{class-name}/{method-name}", preprocessResponse(prettyPrint())
    );
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
        .apply(documentationConfiguration(this.restDocumentation))
        .alwaysDo(document)
        .build();
  }

  @Test
  public void get_member() throws Exception {
    mockMvc.perform(get("/member/{id}", 1L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(document.document(
            pathParameters(
                parameterWithName("id").description("Member's id")
            ),
            responseFields(
                fieldWithPath("email.value").description("The Member's email address"),
                fieldWithPath("address.city").description("The Member's address city"),
                fieldWithPath("address.street").description("The Member's address street"),
                fieldWithPath("address.zipCode").description("The Member's address zipCode")
            )
        ))
        .andExpect(jsonPath("$.email.value", is(notNullValue())))
        .andExpect(jsonPath("$.address.city", is(notNullValue())))
        .andExpect(jsonPath("$.address.street", is(notNullValue())))
        .andExpect(jsonPath("$.address.zipCode", is(notNullValue())))
    ;

  }

}
