package hellojpa;

import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Hibernate SELECT TEST
 */
public class JpaSelect {

  public static void run() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      //SELECT문 수행
      Member findMem = em.find(Member.class, 1L);

      //출력결과 확인
      System.out.println("findMem id = " + findMem.getId());
      System.out.println("findMem name = " + findMem.getUsername());

      tx.commit();
    } catch (Exception e) {
      tx.rollback();  //예외 발생 시 트랜잭션 롤
    } finally {
      em.close();
    }
    emf.close();
  }
}