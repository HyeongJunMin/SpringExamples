package hellojpa;

import domain.Member;
import domain.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaInheritance {

  public static void run() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Movie movie = new Movie();
      movie.setDirector("aaaa");
      movie.setActor("bb");
      movie.setName("바람과함께사라지다");
      movie.setPrice(10000);

      em.persist(movie);

      em.flush();
      em.clear();

      Movie findMovie = em.find(Movie.class, movie.getId());
      System.out.println("findMov : " + findMovie.getName());

      tx.commit();
    } catch (Exception e) {
      tx.rollback();  //예외 발생 시 트랜잭션 롤
    } finally {
      em.close();
    }
    emf.close();
  }
}
