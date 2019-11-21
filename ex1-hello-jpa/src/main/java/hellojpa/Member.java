package hellojpa;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity //JPA로딩될 때 JPA를 사용하는 클래스라고 인식
@Table(name = "MEMBER") //테이블 이름 지정
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;
    @ManyToOne  //Many 멤버 to One 팀
    @JoinColumn(name = "TEAM_ID")   //위 관계에서 조인하는 컬럼 이름을 알려줌(FK)
    private Team team;  //일대일인지, 일대다인지 JPA에게 알려줘야

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
