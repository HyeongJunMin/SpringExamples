package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 상속관계 매핑을 설명하기 위한 엔티티.
 * 자식클래스 : Album, Book, Movie
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn    //DTYPE컬럼을 생성해서 하위 엔티티 이름을 저장한다
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
