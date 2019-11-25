package jpql;

import lombok.*;

import javax.persistence.*;

@Entity //JPA로딩될 때 JPA를 사용하는 클래스라고 인식
@Table(name = "MEMBER") //테이블 이름 지정
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private int age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")   //위 관계에서 조인하는 컬럼 이름을 알려줌(FK)
    private Team team;  //일대일인지, 일대다인지 JPA에게 알려줘야

}
