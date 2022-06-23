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
            // 저장
            Team team = new Team();
            team.setName("TeamA");
//            team.getMembers().add(member); //mappedby로 되어있는 가짜 매핑 : 읽기 전용이라 쓰기는 안된다.
            em.persist(team);


            Member member = new Member();
            member.setUsername("member1");
//            member.setTeamId(team.getId());
//            member.changeTeam(team);//****
//            team.addMember(member);
            member.addTeam(team);


            em.persist(member);

            /*양쪽에 값을 꼭 세팅해줘야함. DB에서 직접 가져오는게 아니라 1차 캐시에서 가져오는거라면, List<Member>에는 암것도 없당*/
//            team.getMembers().add(member);//****

//            em.flush();
//            em.clear(); //1차 캐시 초기화. 이거 이후에는 DB에서 다시 조회해옴.

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("==========================");
            if (members.size() != 0) {
                System.out.println("members.size() = " + members.size());
                for (Member m : members) {
                    System.out.println("m.getUsername() = " + m.getUsername());
                }
            } else {
                System.out.println("List<Member>에 암것도 없음!!");
            }

//            System.out.println("findTeam = " + findTeam);

            System.out.println("==========================");

//
//            Member findMember = em.find(Member.class, member.getId());
//
//            //양방향 연관관계
//            List<Member> memberList = findMember.getTeam().getMembers();
//
//            for (Member m : memberList) {
//                System.out.println("m.getUsername() = " + m.getUsername());
//            }
//
////            System.out.println("findTeam.getName() = " + findTeam.getName());
//
////            Team findTeam = em.find(Team.class, findMember.getTeamId());
//
//            Team newTeam = em.find(Team.class, 100L);
//            findMember.setTeam(newTeam);

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
