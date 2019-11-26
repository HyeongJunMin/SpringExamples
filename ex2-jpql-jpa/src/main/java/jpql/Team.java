package jpql;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Team {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @BatchSize(size = 5)
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList();

}
