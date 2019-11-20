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

    @Id
    private Long id;

    @Column(name = "name")
    private String username;

    private int age;

    @Enumerated(EnumType.STRING)//enum타입 ORDINAL : 숫자로 표현된 순서, STRING : 문자그대로의 이름
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)//Date, Time, Timestamp 세 가지 사용 가능한 enum클래스
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob    //대용량 컨텐츠
    private String description;

}
