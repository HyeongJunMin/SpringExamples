package com.jpamaria.mariaexam.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "server_info")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ServerInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String address;
}
