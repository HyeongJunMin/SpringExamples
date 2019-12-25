package com.hjmin.jpaboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "market_employee")
@Getter
@Setter
@ToString(exclude = "market")
public class MarketEmployee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "market_employee_id")
  private Long id;

  private String name;
  private Integer age;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "market_id")
  private Market market;
}
