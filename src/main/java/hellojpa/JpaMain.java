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

            Member member = new Member();
            member.setUsername("user");
            em.persist(member);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member.getId());
            System.out.println("refMember.getClass() = " + refMember.getClass());

//            refMember.getUsername(); //JPA식 강제 초기화,jpa에는 강제 초기화를 위한 특정한 기능이 없음.

            Hibernate.initialize(refMember);//강제초기화


//            em.detach(refMember);
//            em.close();
            // 영속성 컨텍스트로 관리가 되지 않으면 프록시 초기화 불가


            //프록시 초기화 여부 확인
//            System.out.println("isLoaded = "+ emf.getPersistenceUnitUtil().isLoaded(refMember));


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
