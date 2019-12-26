package com.exam.restdocs.vo;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MarketReqVO {

  private String name;
  private String location;

  private List<Employee> employeeList = new ArrayList();
  private List<Item> itemList = new ArrayList();

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
