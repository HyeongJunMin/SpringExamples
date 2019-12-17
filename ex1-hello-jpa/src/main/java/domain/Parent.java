package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 영속성 전이를 공부하기 위한 엔티
 */
@Entity
@Getter
@Setter
public class Parent {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  /**
   * cascade옵션을 동해 Parent가 영속상태로 바뀔 때 children이 갖는 객체들도 영속 상태로 바뀐다.
   */
  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
  private List<Child> children = new ArrayList<Child>();

  /**
   * 연관관계 편의 메서드
   */
  public void addChild(Child child) {
    children.add(child);
    child.setParent(this);
  }
}
