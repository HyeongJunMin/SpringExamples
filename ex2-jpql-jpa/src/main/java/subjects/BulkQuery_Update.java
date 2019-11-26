package subjects;

import jpql.Member;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class BulkQuery_Update {
    public static void run(EntityManager em, EntityTransaction tx){
        Team team = new Team();
        team.setName("팀A");
        em.persist(team);

        Team team2 = new Team();
        team2.setName("팀B");
        em.persist(team2);

        Member m1 = new Member();
        m1.setUsername("회원1");
        m1.setTeam(team);
        em.persist(m1);

        Member m2 = new Member();
        m2.setUsername("회원2");
        m2.setTeam(team);
        em.persist(m2);

        Member m3 = new Member();
        m3.setUsername("회원3");
        m3.setTeam(team2);
        em.persist(m3);

        em.flush();
        em.clear();

        int resultCount = em.createQuery("update Member m set m.age=20 ")
                .executeUpdate();

        System.out.println("resultCount = " + resultCount);

        tx.commit();
    }
}
