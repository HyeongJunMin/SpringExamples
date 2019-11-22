package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 공유 참조로 인해 발생하는 부작용을 피하기 위해
 * 불변객체로 설계한 클래스
 *
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Address {

    private String city;
    private String street;

    @Column(name = "ZIPCODE")
    private String zipcode;
}
