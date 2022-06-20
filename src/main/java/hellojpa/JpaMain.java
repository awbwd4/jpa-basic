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
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            em.persist(member);

            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                                .setFirstResult(0)
                                .setMaxResults(100)
                                .getResultList();

            System.out.println(result);

            //
            for (Member m1: result) {
                System.out.println("member = " + m1.getName());
            }
//
//            Member findMember = em.find(Member.class, 1L);
//
//            findMember.setName("memberJPA");
//
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
