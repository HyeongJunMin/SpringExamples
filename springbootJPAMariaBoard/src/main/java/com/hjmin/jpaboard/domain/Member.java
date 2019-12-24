package com.hjmin.jpaboard.domain;

import com.hjmin.jpaboard.domain.member.Address;
import com.hjmin.jpaboard.domain.member.Email;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member")
@NoArgsConstructor
@Getter
public class Member {

  @Id
  @GeneratedValue
  private long id;

  @Embedded
  private Email email;

  @Embedded
  private Address address;

  @Builder
  public Member(Email email, Address address) {
    this.email = email;
    this.address = address;
  }
}
