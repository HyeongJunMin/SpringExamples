package com.exam.restdocs.controller;

import lombok.var;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
public class DocsController {

  @GetMapping(value = "/api/index")
  public RepresentationModel showAPIIndex() {
    var index = new RepresentationModel();
    linkTo(MarketController.class).withRel("index");
    return index;
  }
}
