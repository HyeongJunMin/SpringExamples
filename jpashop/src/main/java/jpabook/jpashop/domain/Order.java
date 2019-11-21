package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "ORDERS") //ORDER가 예약어로 제한되어 있는 경우를 대비하기 위해 테이블을 ORDERS로 명명
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Order {

    @Id @GeneratedValue //strategy를 생략하면 디폴트 (AUTO) Strategy
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne          /*오더 입장에서 Many To One */
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addOrderItem(OrderItem orderItem){  //양방향 연관관계에서 버그 발생을 줄이기 위한 메소드
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
