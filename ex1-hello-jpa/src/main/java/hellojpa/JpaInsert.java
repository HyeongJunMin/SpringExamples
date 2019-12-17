package hellojpa;

import domain.Member;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaInsert {
  public static void run() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    //트랜잭션은 아주 중요한 역할을 함
    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      //엔티티매니저로 값을 넣고뺌
      Team team = new Team();
      team.setName("TeamA");
      em.persist(team);

      Member mem = new Member();
      mem.setUsername("mem1");
      //mem.setTeamId(team.getId());    //영속상태인 team의 id를 가져옴 - 연관관계 매핑이 아직 안되므로 객체자체를 가져오지 않았음
      mem.setTeam(team);  //연관관계 매핑을 통해 객체 자체를 사용
      em.persist(mem);

      em.flush();
      em.clear();

      System.out.println("flush + clear");

      Member findMem = em.find(Member.class, mem.getId());
      //Team findTeam = findMem.getTeam();
      //System.out.println("findTeam = " + findTeam.toString());

      List<Member> members = findMem.getTeam().getMembers();
      for (Member m : members) {
        System.out.println("mem " + m.getUsername());
      }

      tx.commit();
    } catch (Exception e) {
      tx.rollback();  //예외 발생 시 트랜잭션 롤
    } finally {
      em.close();
    }
    emf.close();
  }
}
