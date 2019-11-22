package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 1:1 연관관계 매핑 학습을 위한 클래스
 * Member와 1:1 매핑
 */
@Entity
public class Locker {

    @Id @GeneratedValue
    private Long id;

    private String name;
}
