package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;

    private String name;

    /**team-member가 일대다 관계인 경우
     *   @JoinColumn(name = "TEAM_ID")
     *  @OneToMany
     * **/
    @OneToMany(mappedBy = "team") //member-team이 다대일 관계인 경우, team에서 member
    private List<Member> members = new ArrayList<>();
    // member-team이 다대일 관계인 경우
    // 그냥은 이게 필요는 없지만
    // team에서 member를 읽어야 할 일이 많을 경우 이렇게 해주면 됨.



    //mappedBy = "team "
    //          : Member엔티티에 있는 team필드임

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

//    public void addMember(Member member) {
//        member.setTeam(this);
//        members.add(member);
//    }

//    @Override
//    public String toString() {
//        return "Team{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", members=" + members +
//                '}';
//    }
}
