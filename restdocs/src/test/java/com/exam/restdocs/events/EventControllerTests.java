package com.exam.restdocs.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static com.jayway.jsonpath.internal.JsonFormatter.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EventControllerTests {

  @Autowired
  MockMvc mockMvc;

  //잭슨 json이 의존성으로 등록되어있으면 등록되어 있는 빈
  @Autowired
  ObjectMapper objectMapper;

  @Test
  public void createEvent() throws Exception{
    Event event = Event.builder()
          .name("spring")
          .description("spring boot REST API")
          .beginEnrollmentDateTime(LocalDateTime.of(2019,12,30,5,5,5))
          .closeEnrollmentDateTime(LocalDateTime.of(2019,12,31,5,5,5))
          .beginEventDateTime(LocalDateTime.of(2019,12,31,5,5,5))
          .endEventDateTime(LocalDateTime.of(2019,12,31,5,5,5))
          .basePrice(100)
          .maxPrice(200)
          .limitOfEnrollment(100)
          .location("몽촌토성역")
          .build();

    mockMvc.perform(post("/api/events/")
                .contentType(MediaType.APPLICATION_JSON)//요청 타입은 JSON이다
                .accept(MediaTypes.HAL_JSON)//HAL JSON을 돌려달라
                .content(objectMapper.writeValueAsString(event))//objectMapper가 json string으로 바꿔줌
              )
          .andDo(print())
          .andExpect(status().isCreated())
          .andExpect(jsonPath("id").exists())//id가 있는지 확인
          .andExpect(jsonPath("_links.self").exists())
          .andExpect(jsonPath("_links.markets").exists())
          .andExpect(jsonPath("_links.update-market").exists())
          ;
  }
}
