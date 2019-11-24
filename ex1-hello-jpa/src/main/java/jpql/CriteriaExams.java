package jpql;

import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CriteriaExams {
    public static void run() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //Criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> from = query.from(Member.class);

            //CriteriaQuery<Member> cq = query.select(from).where(cb.equal(from.get("username"), "kim"));
            CriteriaQuery<Member> cq = query.select(from);
            String username = "name1";
            if(username != null){
                cq = cq.where(cb.equal(from.get("username"), "kim"));
            }

            List<Member> resultList = em.createQuery(cq).getResultList();

            tx.commit();
        }catch (Exception e){
            tx.rollback();  //예외 발생 시 트랜잭션 롤
        }finally {
            em.close();
        }
        emf.close();
    }
}
