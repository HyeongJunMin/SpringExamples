package com.exam.restdocs.domain;

import org.springframework.hateoas.RepresentationModel;

public class MarketResource extends RepresentationModel {
  private Market market;

  public MarketResource(Market market) {
    this.market = market;
  }

  public Market getMarket() {
    return market;
  }
}
