package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("A")    //DB 테이블 이름이 A인데 엔티티 이름을 Album으로 가져가고 싶은 경우
public class Album extends Item {
  private String artist;
}
