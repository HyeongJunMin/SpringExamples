package domain;

import com.sun.xml.internal.rngom.parse.host.Base;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //JPA로딩될 때 JPA를 사용하는 클래스라고 인식
@Table(name = "MEMBER") //테이블 이름 지정
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)  //Many 멤버 to One 팀
    @JoinColumn(name = "TEAM_ID")   //위 관계에서 조인하는 컬럼 이름을 알려줌(FK)
    private Team team;  //일대일인지, 일대다인지 JPA에게 알려줘야

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    //@OneToMany(mappedBy = "member")
    //private List<MemberProduct> memberProducts = new ArrayList<MemberProduct>();

    /*모든 엔티티는 DB에 값을 저장할 떄 마다 저장시점과 주체를 저장해야하는 룰이 있는 경우*/


    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
