package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS") //ORDER가 예약어로 제한되어 있는 경우를 대비하기 위해 테이블을 ORDERS로 명명
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Order {

    @Id @GeneratedValue //strategy를 생략하면 디폴트 (AUTO) Strategy
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
