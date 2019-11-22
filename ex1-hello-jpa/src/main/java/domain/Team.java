package domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Team extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    //팀으로 매핑이 되어있다는 내용. team은 연관관계 주인의 변수
    //연관관계의 주인이 아니기 때문에 members의 값이 어떻게 바뀌든 DB에는 영향이 가지 않
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList();
}
