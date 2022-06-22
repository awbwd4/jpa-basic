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
            Member member1 = new Member();
            member1.setUsername("A");
            Member member2 = new Member();
            member2.setUsername("B");
            Member member3 = new Member();
            member3.setUsername("C");
            Member member4 = new Member();
            member4.setUsername("C");
            Member member5 = new Member();
            member5.setUsername("C");




            System.out.println("==============");
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);
            em.persist(member5);
            System.out.println(member1.getId());
            System.out.println(member2.getId());
            System.out.println(member3.getId());
            System.out.println(member4.getId());
            System.out.println(member5.getId());
            System.out.println("==============");

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
