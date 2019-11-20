package hellojpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "TEAM_ID")
    private Long teamId;
}
