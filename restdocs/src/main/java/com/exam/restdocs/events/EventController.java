package com.exam.restdocs.events;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {

  @PostMapping
  public ResponseEntity createEvent(@RequestBody Event event) {
    URI uri = linkTo(EventController.class).slash("{id}").toUri();
    event.setId(10);

    EventResource eventResource = new EventResource(event);

    eventResource.add(linkTo(EventController.class).withSelfRel());
//    eventResource.add(linkTo(EventController.class).withRel("query-events"));

    return ResponseEntity.created(uri).body(eventResource);
  }
}
