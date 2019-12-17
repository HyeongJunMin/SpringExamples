package proxy;

import domain.Member;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaProxy {

  public static void run() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Member mem = new Member();
      mem.setUsername("helloProxy");

      em.persist(mem);
      em.flush();
      em.clear();

      Member findMem = em.getReference(Member.class, mem.getId());
      System.out.println("findMem = " + findMem.getClass());  //hibernate가 만든 가짜(Proxy) 클래스
      System.out.println("findMem.getId() = " + findMem.getId());
      System.out.println("findMem.getUsername() = " + findMem.getUsername());

      tx.commit();
    } catch (Exception e) {
      tx.rollback();  //예외 발생 시 트랜잭션 롤
    } finally {
      em.close();
    }
    emf.close();
  }


}
