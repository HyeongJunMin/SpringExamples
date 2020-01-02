package com.exam.restdocs.events;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.RepresentationModel;

public class EventResource extends RepresentationModel {

  @JsonUnwrapped  //event로 묶인 json을 풀어주는 역할
  private Event event;

  public EventResource(Event event) {
    this.event = event;
  }

  public Event getEvent() {
    return event;
  }
}
