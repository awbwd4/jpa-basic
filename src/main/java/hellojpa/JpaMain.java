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
            // 비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            //영속
//            System.out.println("===before===");
//            em.persist(member); // 1차 캐시에 저장.
//            System.out.println("===after===");

//            Member findMember = em.find(Member.class, 101L); // 1차 캐시에 있는걸 조회.

            Member findMember1 = em.find(Member.class, 101L); // DB에서 조회
            Member findMember2 = em.find(Member.class, 101L); // 1차 캐시에 있는걸 조회.


            System.out.println("result = "+(findMember1 == findMember2));
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
