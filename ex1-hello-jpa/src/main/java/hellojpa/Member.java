package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //JPA로딩될 때 JPA를 사용하는 클래스라고 인식
@Table(name = "MEMBER") //테이블 이름 지정
public class Member {

    @Id //pk임을 알려줌
    private Long id;
    @Column(name = "NAME")  //컬럼이름 지정
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
