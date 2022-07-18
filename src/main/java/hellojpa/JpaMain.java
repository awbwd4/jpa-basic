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
            member.setUsername("hello");
            member.setHomeAddress(new Address("aaaa", "bbbb", "ccccc"));
            member.getFavoriteFoods().add("CHIKEN");
            member.getFavoriteFoods().add("PIZZA");
            member.getFavoriteFoods().add("JOKBAL");

//            member.getAddressHistory().add(new Address("old1", "street1", "zip1"));
//            member.getAddressHistory().add(new Address("old2", "street2", "zip2"));
            member.getAddressEntities().add(new AddressEntity("old1", "street1", "zip1"));
            member.getAddressEntities().add(new AddressEntity("old2", "street2", "zip2"));

            em.persist(member);

            em.flush();
            em.clear();

            //


            Member findMember = em.find(Member.class, member.getId());



            findMember.setHomeAddress(new Address("new", "new", "new"));


            //치킨 -> 한식, String 변경
            System.out.println("========치킨 -> 한식======");
            findMember.getFavoriteFoods().remove("CHIKEN");
            findMember.getFavoriteFoods().add("한식");


            // 주소 변경
//            findMember.getAddressHistory().remove(new Address("old1", "street1", "zip1"));
//            findMember.getAddressHistory().add(new Address("new", "street1", "zip1"));


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
