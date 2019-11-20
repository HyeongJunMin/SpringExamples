package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaUpdate {
    public static void run(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //SELECT문 수행을 통해 조건에 맞는 객체 리턴
            Member findMem = em.find(Member.class, 1L);

            //객체수정 - 자바컬렉션을 다루는 방법과 유사.
            findMem.setName("바뀐이름");

            tx.commit();
        }catch (Exception e){
            tx.rollback();  //예외 발생 시 트랜잭션 롤
        }finally {
            em.close();
        }
        emf.close();
    }
}
