package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SequenceGenerator(//테이블마다 시퀀스 따로 관리
        name="MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 50 //시퀀스 한번에 증가하는 수
        // 메모리에 미리 시퀀스 50개를 호출해서 올려둔다
        // 이러면 시퀀스 올릴때마다 DB에 접속할 필요 없이
        // 50단위씩은 메모리에서 처리 가능
        )
//@TableGenerator(
//        name="MEMBER_SEQ_GENERATOR",
//        table="MY_SEQUENCES",
//        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
@Entity//JPA가 관리하는 객체.
public class Member extends BaseEntity{

    /**fields**/

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY) // Member 엔티티 조회시 프록시로 조회됨.
    @JoinColumn(name="TEAM_ID")
    private Team team;

    //    @Column(name="TEAM_ID")
//    private Long teamId;

//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID")
//    private Locker locker;
//    // Member가 주인인 1:1 관계. 실제 테이블에도 Member가 fk를 가지고 있음.
//
////    @ManyToMany
////    @JoinTable(name="MEMBER_PRODUCT")
////    private List<Product> products = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member")
//    private List<MemberProduct> memberProducts = new ArrayList<>();



    /**getter setter**/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void addTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);//**
    }

//    @Override
//    public String toString() {
//        return "Member{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", team=" + team +
//                '}';
//    }

    //    @Id //pk
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
//    private Long id; //int는 10억이 넘어가면 다시 0부터 시작됨 id 같은건 그냥 Long쓰는게 나음
//    //IDENTITY : 기본키 생성을 DB에 위임. flush되는 sql을 보면 id 값이 null이다
//
//
//    @Column(name = "name", nullable = false)//테이블 칼럼명
//    private String username;
//
//    public Member() {
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
}
