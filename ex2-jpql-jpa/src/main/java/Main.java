import subjects.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //FetchJoin_Entity.run(em, tx);
            //FetchJoin_Collection.run(em, tx);
            //UsingEntityDirectly.run(em, tx);
            //NamedQuery.run(em, tx);
            BulkQuery_Update.run(em, tx);

        }catch (Exception e){
            tx.rollback();  //예외 발생 시 트랜잭션 롤
        }finally {
            em.close();
        }
        emf.close();
    }
}
