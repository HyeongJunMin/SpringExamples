package com.exam.restdocs.vo;

import com.exam.restdocs.domain.Market;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MarketReqVO {

  private String name;
  private String location;

  //private List<Employee> employeeList = new ArrayList();
  private Set<Employee> employeeSet = new LinkedHashSet();

  //private List<Item> itemList = new ArrayList();
  private Set<Item> itemSet = new LinkedHashSet();

  @Getter
  @Setter
  @ToString
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Employee {
    private String name;
    private Integer age;
  }

  @Getter
  @Setter
  @ToString
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Item {
    private String name;
    private String category;
    private Integer quantity;
  }
}
