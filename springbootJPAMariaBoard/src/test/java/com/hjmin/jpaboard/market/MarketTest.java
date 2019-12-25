package com.hjmin.jpaboard.market;

import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MarketTest {

  @Rule
  public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
  @Autowired
  private WebApplicationContext context;
  private MockMvc mockMvc;
  private RestDocumentationResultHandler document;

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

  @Ignore
  @Test
  @Rollback(false)
  public void a_createMarket() throws Exception{
    mockMvc.perform(post("/market/create")).andDo(print());
  }

  @Test
  public void b_getAllMarketInfo() throws Exception{
    log.info("start get all ");
    mockMvc.perform(post("/market/getall")).andDo(print());
  }
}
