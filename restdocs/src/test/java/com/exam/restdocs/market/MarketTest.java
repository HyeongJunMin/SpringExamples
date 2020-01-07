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

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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

  @Ignore
  @Test
  public void createMarket() throws Exception{
    String jsonData = FileUtils.readFileToString(new File("src/test/resources/market_1.json"));
    mockMvc
        .perform(
            post("/market/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
        .andDo(print())
        //.andDo(document("create-event"))
        .andDo(document("create-event",
            links(
                linkWithRel("self").description("link to self")
            ),
            requestHeaders(
                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                headerWithName(HttpHeaders.CONTENT_TYPE).description("header content type")
            ),
            requestFields(
                fieldWithPath("name").description("Name"),
                fieldWithPath("description").description("desc")
            ),
            responseFields(
                fieldWithPath("respName").description("resp name")
            )
            )
        );
  }

  @Test
  public void createMarketEntity() throws Exception{
    LinkedHashSet<Employee> employees = new LinkedHashSet();
    Employee employee = new Employee();
    employee.setName("emp1");
    employee.setAge(20);
    employees.add(employee);

    LinkedHashSet<Item> items = new LinkedHashSet();
    Item item = new Item();
    item.setCategory("grocery");
    item.setName("corn");
    item.setQuantity(50);
    items.add(item);

    Market market = Market.builder()
        .marketName("marketName")
        .location("norvrant")
        .employees(employees)
        .items(items)
        .build();

    mockMvc.perform(post("/market/create/entity")
        .contentType(MediaType.APPLICATION_JSON)//요청 타입은 JSON이다
        .accept(MediaTypes.HAL_JSON)//HAL JSON을 돌려달라
        .content(objectMapper.writeValueAsString(market))//objectMapper가 json string으로 바꿔줌
    )
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("market.id").exists())//id가 있는지 확인
        .andExpect(jsonPath("_links.self").exists())
        .andExpect(jsonPath("_links.markets").exists())
        .andExpect(jsonPath("_links.update-market").exists())
        //적용한 스니펫 : links,
        .andDo(document("create-market",
              links(
                  linkWithRel("self").description("link to self"),
                  linkWithRel("markets").description("link to show all markets"),
                  linkWithRel("update-market").description("link to update a market")
              )
            )
        )
    ;
  }
}
