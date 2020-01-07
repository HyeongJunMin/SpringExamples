package com.exam.restdocs.controller;


import com.exam.restdocs.domain.Market;
import com.exam.restdocs.domain.MarketResource;
import com.exam.restdocs.events.EventController;
import com.exam.restdocs.vo.MarketReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.Aware;
import org.springframework.hateoas.server.core.LastInvocationAware;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.ControllerLinkRelationProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;

@Slf4j
@RestController
@RequestMapping(value = "/market")
public class MarketController {

  @RequestMapping(value ="/create", method = RequestMethod.POST)
  public void createNewMarket(@RequestBody MarketReqVO reqVO) {
    URI uri = linkTo(MarketController.class).slash("{id}").toUri();
    log.info("req : {}", reqVO.toString());
  }

  @RequestMapping(value ="/create/entity", method = RequestMethod.POST)
  public ResponseEntity createNewMarketEntity(@RequestBody Market market) {
    ControllerLinkBuilder slash = linkTo(MarketController.class).slash("{id}");
    URI uri = slash.toUri();
    log.info("market : {}", market.toString());
    market.setId(10L);

    MarketResource marketResource = new MarketResource(market);
    marketResource.add(linkTo(MarketController.class).withSelfRel());
    marketResource.add(linkTo(MarketController.class).withRel("markets"));
    marketResource.add(slash.withRel("update-market"));

    return ResponseEntity.created(uri).body(marketResource);
  }

}
