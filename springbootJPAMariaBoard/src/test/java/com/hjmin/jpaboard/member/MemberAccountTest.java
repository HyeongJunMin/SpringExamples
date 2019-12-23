package com.hjmin.jpaboard.member;

import com.hjmin.jpaboard.TestConfig;
import com.hjmin.jpaboard.controller.MemberRESTController;
import com.hjmin.jpaboard.domain.Member;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class MemberAccountTest {

  @Rule
  public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
  @Autowired
  private WebApplicationContext context;
  private MockMvc mockMvc;
  private RestDocumentationResultHandler document;

  @Autowired
  private MemberRESTController memberRESTController;

  @Before
  public void setUp() throws Exception {
    this.document = document("{class-name}/{method-name}", preprocessResponse(prettyPrint()));
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
        .apply(documentationConfiguration(this.restDocumentation)
          .uris().withScheme("https").withHost("hjmin.com").withPort(443))
        .alwaysDo(document)
        .build();
  }


  @Test
  public void signupTest() throws Exception {
    Member mem = new Member();
    mem.setName("min");
    mem.setPw("1234");
    mockMvc.perform(
        post("/member/signup")
            .param("name","min"));
  }
}
