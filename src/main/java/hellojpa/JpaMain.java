package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamA");
            em.persist(teamB);

            Member memberA = new Member();
            memberA.setUsername("memberA");
            memberA.addTeam(teamA);
            em.persist(memberA);


            Member memberB = new Member();
            memberB.setUsername("memberB");
            memberB.addTeam(teamB);
            em.persist(memberB);

            em.flush();
            em.clear();

//            List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
            
            //fetch join
            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();


            //
//            Member m = em.find(Member.class, member.getId());
//            System.out.println("refMember.getClass() = " + m.getClass());
//            System.out.println("refMember.getTeam().getClass() = " + m.getTeam().getClass());
//
//
//            //실제 team을 사용할때 초기화한다
//            System.out.println("refMember.getTeam().getName() = " + m.getTeam().getName());
//
//            //물론 그래도 한번 프록시면 계속 프록시임ㅇㅇ
//            System.out.println("refMember.getTeam().getClass() = " + m.getTeam().getClass());


            tx.commit();
        } catch (Exception e) {
            System.out.println("catch");
            tx.rollback();
            System.out.println("e = " + e);
        }finally {
            System.out.println("finally");
            em.close();
        }
        emf.close();
    }
}
