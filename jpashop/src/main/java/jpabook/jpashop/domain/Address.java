package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 공유 참조로 인해 발생하는 부작용을 피하기 위해
 * 불변객체로 설계한 클래스
 */
@Embeddable //값타입
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(doNotUseGetters = false)  //값타입 비교를 위해 선언, 게터를 호출해야 프록시를 사용할 때 문제가 안됨
public class Address {

  private String city;
  private String street;

  @Column(name = "ZIPCODE")
  private String zipcode;
}
