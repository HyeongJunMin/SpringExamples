package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Item만 단독으로 사용하지 않고 Album, Book, Movie에서 상속받아 사용한다.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Item extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "ITEM_ID")
  private Long id;

  private String name;
  private int price;
  private int stockQuantity;

  @ManyToMany(mappedBy = "items") //예시이기 때문에 다대다를 사용하였으나 실제로는 사용하지 않도록 한다.
  private List<Category> categories = new ArrayList<>();
}
