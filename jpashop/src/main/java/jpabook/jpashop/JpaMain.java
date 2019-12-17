package jpabook.jpashop;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    //트랜잭션은 아주 중요한 역할을 함
    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Book b = new Book();
      b.setIsbn("isbn");
      b.setAuthor("author");
      em.persist(b);

      Order o = new Order();
      em.persist(o);

      tx.commit();
    } catch (Exception e) {
      tx.rollback();  //예외 발생 시 트랜잭션 롤
    } finally {
      em.close();
    }
    emf.close();
  }
}
