package com.exam.restdocs.controller;


import com.exam.restdocs.vo.MarketReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/market")
public class MarketController {

  @RequestMapping(value ="/create", method = RequestMethod.POST)
  public void createNewMarket(@RequestBody MarketReqVO reqVO) {
    log.info("req : {}", reqVO.toString());
  }

}
