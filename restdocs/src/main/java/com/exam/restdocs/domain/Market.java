package com.exam.restdocs.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;

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
  private Long id;

  private String marketName;
  private String location;

  @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
  private Set<Employee> employees = new LinkedHashSet();;

  @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
  private Set<Item> items = new LinkedHashSet();
}
