package subjects;

import jpql.Member;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class FetchJoin_Collection {
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

        //팀은 2개지만 팀원이 2명인 팀은 데이터 행이 2개이다. 팀원이 100명이면 데이터 행 100줄...
        //데이터 왜곡 발생
        String queryCollection = "select t from Team t join fetch t.members";

        //DISTINCT를 통해 데이터 왜곡 방지
        String querySQLDistinct = "select distinct t from Team t join fetch t.members";


        //List<Team> resultList = em.createQuery(queryCollection, Team.class).getResultList();
        List<Team> resultList = em.createQuery(querySQLDistinct, Team.class).getResultList();

        for (Team ttt: resultList
        ) {
            System.out.println("team : " + ttt.getName() + ", ttt.getMembers().size() = " + ttt.getMembers().size());
            for (Member m:ttt.getMembers()
                 ) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }
        }

        tx.commit();
    }
}
