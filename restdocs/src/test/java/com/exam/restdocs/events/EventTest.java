package com.exam.restdocs.events;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setMaxLengthForSingleLineDescription;

@Slf4j
public class EventTest {

  @Test
  public void builder() {
    //빌더 유무 확인
    Event event = Event.builder()
                    .name("Spring REST API")
                    .description("REST API DEV")
                    .build();
    assertThat(event).isNotNull();
    log.info("event builder ok");
  }

  @Test
  public void javaBeanSpec() {
    Event event = new Event();
    String name = "Event";
    String description = "Event java bean spec";
    event.setName(name);
    event.setDescription(description);

    assertThat(event.getName()).isEqualTo(name);
    assertThat(event.getDescription()).isEqualTo(description);
  }

}