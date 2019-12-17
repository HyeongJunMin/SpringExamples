package hellojpa;

import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaPersistenceContext {

  public static void run() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      //생명주기 : 비영속
      Member member = new Member();
      member.setId(100L);
      member.setUsername("JPAJPA");

      //생명주기 : 영속, INSERT 쿼리는 BEFORE-AFTER사이가 아니고 나중에 수행 - Transaction 커밋 시점에 수행됨
      System.out.println("BEFORE");
      em.persist(member);
      System.out.println("AFTER");

      tx.commit();

      //생명주기 : 준영속 - JPA와 관련없는 객체가 됨
      em.detach(member);

    } catch (Exception e) {
      tx.rollback();  //예외 발생 시 트랜잭션 롤
    } finally {
      em.close();
    }
    emf.close();
  }

}
