package com.hjmin.jpaboard.controller;

import com.hjmin.jpaboard.domain.Market;
import com.hjmin.jpaboard.domain.MarketEmployee;
import com.hjmin.jpaboard.domain.MarketItem;
import com.hjmin.jpaboard.repository.MarketRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/market")
@AllArgsConstructor
public class MarketRestController {

  private final MarketRepository marketRepository;

  @RequestMapping(value = "/conn", method = RequestMethod.POST)
  public void chkConnection() {
    log.info("chk connection ok");
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public void createMarket() {

    Market market = new Market();
    market.setName("시장1");
    market.setEmployees(new LinkedHashSet());
    market.setItems(new LinkedHashSet());

    for (int i = 1; i < 5; i++) {
      MarketEmployee emp = new MarketEmployee();
      emp.setName("직원" + i);
      emp.setMarket(market);
      market.getEmployees().add(emp);

      MarketItem item = new MarketItem();
      item.setName("물건" + i);
      item.setMarket(market);
      market.getItems().add(item);
    }

    marketRepository.save(market);

  }

  @RequestMapping(value = "/getall", method = RequestMethod.POST)
  public void getAllMarket() {
    List<Market> all = marketRepository.findAll();
    for (Market market1 : all) {
      log.info("market info : {}", market1);
    }
  }
}
