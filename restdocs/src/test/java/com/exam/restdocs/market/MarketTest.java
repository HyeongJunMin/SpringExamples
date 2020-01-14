package com.exam.restdocs.market;

import com.exam.restdocs.common.RestDocsConfiguration;
import com.exam.restdocs.domain.Employee;
import com.exam.restdocs.domain.Item;
import com.exam.restdocs.domain.Market;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.util.LinkedHashSet;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
public class MarketTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

//  @Ignore
  @Test
  public void createMarket() throws Exception{
    String jsonData = FileUtils.readFileToString(new File("src/test/resources/market_1.json"));
    mockMvc
        .perform(
            post("/market/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
              //.content(objectMapper.writeValueAsString(market))//objectMapper가 json string으로 바꿔줌
        .andDo(print())
        .andDo(document("create-market",
              links(
                  halLinks(),
                  linkWithRel("self").description("link to self")
              ),
              requestHeaders(
                  headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                  headerWithName(HttpHeaders.CONTENT_TYPE).description("header content type")
              ),
              relaxedRequestFields(
                  fieldWithPath("marketName").description("Name"),
                  fieldWithPath("location").description("desc")
              ),
              responseHeaders(
                  headerWithName(HttpHeaders.LOCATION).description("resp header location")
              )
//            ,
//              responseFields(
//                  fieldWithPath("respName").description("resp name"),
//                  subsectionWithPath("respSub").description("sub section")
//              )
            )
        );
  }

  @Test
  public void createMarketEntity() throws Exception{
    String body = FileUtils.readFileToString(new File("src/test/resources/market_1.json"));

    mockMvc.perform(post("/market/create/entity")
        .contentType(MediaType.APPLICATION_JSON)//요청 타입은 JSON이다
        .accept(MediaTypes.HAL_JSON)//HAL JSON을 돌려달라
        .content(body)
    )
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("market.id").exists())//id가 있는지 확인
        .andExpect(jsonPath("_links.self").exists())
        .andExpect(jsonPath("_links.markets").exists())
        .andExpect(jsonPath("_links.update-market").exists())
        //적용한 스니펫 : links
        .andDo(document("create-market",
              links(//링크정보
                  linkWithRel("self").description("link to self"),
                  linkWithRel("markets").description("link to show all markets"),
                  linkWithRel("update-market").description("link to update a market"),
                  linkWithRel("profile").description("link to update a market")
              ),
              requestHeaders(//요청 헤더
                  headerWithName(HttpHeaders.ACCEPT).description("accept type"),
                  headerWithName(HttpHeaders.CONTENT_TYPE).description("content type")
              ),
              requestFields(//요청 필드
                  fieldWithPath("marketName").description("name of new market"),
                  fieldWithPath("location").description("location of market"),
                  fieldWithPath("employees.[].name").description("name of employee of market"),
                  fieldWithPath("employees.[].age").description("name of employee of market"),
                  fieldWithPath("items.[].name").description("name of item of market"),
                  fieldWithPath("items.[].category").description("category of item of market"),
                  fieldWithPath("items.[].quantity").description("quantity of item of market")
              ),
              responseHeaders(
                  headerWithName(HttpHeaders.LOCATION).description("accept type"),
                  headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL JSON type")
              ),
              //responseFields(
              relaxedResponseFields(//relaxed : 문서의 일부분만 확인해도 되게끔 설정해주는 prefix
                  //fieldWithPath : 응답의 필드를 기술하기 위한 메소드
                  //subsectioniiWithPath : 하위섹션에 대한 정보를 기술하기 위한 메소드
                  subsectionWithPath("market").description("info of market"),
                  fieldWithPath("market.id").description("id of market"),
                  fieldWithPath("market.marketName").description("name of new market"),
                  fieldWithPath("market.location").description("location of market"),
                  subsectionWithPath("market.employees[]").description("employees of market"),
                  fieldWithPath("market.employees[].id").description("id of employee of market"),
                  fieldWithPath("market.employees.[].name").description("name of employee of market"),
                  fieldWithPath("market.employees.[].age").description("name of employee of market"),
                  fieldWithPath("market.employees.[].market").description("market of employee of market"),
                  subsectionWithPath("market.items").description("items of market"),
                  fieldWithPath("market.items.[].id").description("id of item of market"),
                  fieldWithPath("market.items.[].name").description("name of item of market"),
                  fieldWithPath("market.items.[].category").description("category of item of market"),
                  fieldWithPath("market.items.[].quantity").description("quantity of item of market"),
                  fieldWithPath("market.items.[].market").description("market of item of market"),
                  fieldWithPath("_links.profile").description("profile")
              )
            )
        )
    ;
  }
}
