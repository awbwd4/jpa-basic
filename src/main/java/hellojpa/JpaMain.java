package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //영속
            Member memberA = new Member(201L, "member201");
            Member memberB = new Member(202L, "member202");
            Member memberC = new Member(203L, "member203");
            em.persist(memberA);
            em.persist(memberB);
            em.persist(memberC);

            List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

            for (Member member : members ) {
                System.out.println("member.getName() = " + member.getName());
            }
//            em.flush(); // 이 시점에 DB로 인서트 즉시 시행

            System.out.println("=================");
            tx.commit();
        } catch (Exception e) {
            System.out.println("catch");
            tx.rollback();
        }finally {
            System.out.println("finally");
            em.close();
        }
        emf.close();
    }
}
