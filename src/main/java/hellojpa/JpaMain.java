package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

            Member member1 = new Member();
            member.setUsername("user1");

            Member member2 = new Member();
            member.setUsername("user2");

            em.persist(member);
            em.persist(member1);
            em.persist(member2);

            em.flush();
            em.clear();

//            Member m1 = em.find(Member.class, member1.getId());
//
//            Member m2 = em.getReference(Member.class, member2.getId());
//
//            System.out.println("m1 == m2 :  " + (m1.getClass()==m2.getClass()));
//            System.out.println("m1 == m2 :  " + (m1 instanceof Member));
//            System.out.println("m1 == m2 :  " + (m2 instanceof Member));

            Member reference = em.getReference(Member.class, member1.getId());
//            System.out.println("reference.getClass() = " + reference.getClass());
//            //동일한 영속성 컨텍스트 안에서는 getReference를 해도 1차 캐시에 이미 있는 실제 엔티티를 가져옴
//            System.out.println("a ==== a : "+(m1 == reference));
//            System.out.println("m1.class() : "+ m1.getClass());
//            System.out.println("reference.class() : "+ reference.getClass());

            em.detach(reference);
//            em.close();

            System.out.println("reference.getUsername()    :    "+reference.getUsername());


            System.out.println("/////////////////조회//////////////////");


//            Member findMember = em.find(Member.class, member.getId());
            Member findMeberR = em.getReference(Member.class, member.getId());//프록시 객체
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getUsername() = " + findMember.getUsername());
            System.out.println("findMeberR = " + findMeberR.getClass());
            System.out.println("findMeberR.getId() = " + findMeberR.getId());

            //여기에서 실제 엔티티 생성및 초기화됨
            System.out.println("findMeberR.getUsername() = " + findMeberR.getUsername());
            // 두번째부터는 프록시에 값이 있으므로 초기화 생성x
            System.out.println("findMeberR.getUsername() = " + findMeberR.getUsername());
            System.out.println("findMeberR = " + findMeberR.getClass());
            // 초기화는 됐지만 여전히 프록시는 프록시다 실제 엔티티 아님. 걍 프록시 내 값이 채워지는것.

            
            
            
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
