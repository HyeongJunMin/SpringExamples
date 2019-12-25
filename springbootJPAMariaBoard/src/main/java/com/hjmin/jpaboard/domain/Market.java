package com.hjmin.jpaboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "market")
@Getter
@Setter
@ToString
public class Market {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "market_id")
  private Long id;

  private String name;

  @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
  private Set<MarketEmployee> employees = new LinkedHashSet();

  @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
  private Set<MarketItem> items = new LinkedHashSet();
}
