package subjects;

import jpql.Member;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class FetchJoin_Entity {
  public static void run(EntityManager em, EntityTransaction tx) {
    Team team = new Team();
    team.setName("팀A");
    em.persist(team);

    Team team2 = new Team();
    team2.setName("팀B");
    em.persist(team2);

    Member m1 = new Member();
    m1.setUsername("회원1");
    m1.setTeam(team);
    em.persist(m1);

    Member m2 = new Member();
    m2.setUsername("회원2");
    m2.setTeam(team);
    em.persist(m2);

    Member m3 = new Member();
    m3.setUsername("회원3");
    m3.setTeam(team2);
    em.persist(m3);

    em.flush();
    em.clear();

    //fetch없이 나간 쿼리는 select를 총 3번 수행한다.
    //회원1 팀A는 sql에서, 회원2 팀A는 1차 캐시에서, 회원3 팀B는 sql에서 가져왔다.
    String queryNonFetch = "select m from Member m";

    //요렇게 해주면 지연 로딩으로 설정된 엔티티여도 즉시로딩으로 수행한다. select를 총 1번 수행한다.
    //String queryFetch = "select m from Member m left join fetch m.team";  //left outer join
    String queryFetch = "select m from Member m join fetch m.team";


    //List<Member> resultList = em.createQuery(queryNonFetch, Member.class).getResultList();
    List<Member> resultList = em.createQuery(queryFetch, Member.class).getResultList();
    for (Member mmm : resultList
    ) {
      System.out.println("username : " + mmm.getUsername() + ", mmm.getTeam().getName() = " + mmm.getTeam().getName());
    }

    tx.commit();
  }
}
