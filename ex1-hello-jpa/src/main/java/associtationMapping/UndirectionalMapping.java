package associtationMapping;

import domain.Member;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UndirectionalMapping {

  public static void wrongWay(EntityManagerFactory emf, EntityManager em, EntityTransaction tx) {
    Team team = new Team();
    team.setName("TeamA");
    em.persist(team);

    Member mem = new Member();
    mem.setUsername("member1");

    //역박향(주인이 아닌 방향)만 연관관계 설정한 경우
    team.getMembers().add(mem);
    em.persist(mem);

    //결과 : teamid에 null이들어감
  }

  public static void rightWay(EntityManagerFactory emf, EntityManager em, EntityTransaction tx) {
    Team team = new Team();
    team.setName("TeamA");
    em.persist(team);

    Member mem = new Member();
    mem.setUsername("member1");
    //연관관계의 주인에게 team 저장
    mem.setTeam(team);

    em.persist(mem);

    //결과 : 정상
  }

  public static void run() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    //트랜잭션은 아주 중요한 역할을 함
    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      //wrongWay(emf, em, tx);
      rightWay(emf, em, tx);

      tx.commit();
    } catch (Exception e) {
      tx.rollback();  //예외 발생 시 트랜잭션 롤
    } finally {
      em.close();
    }
    emf.close();
  }
}
