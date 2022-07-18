package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static javax.persistence.CascadeType.*;

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
public class Member {

    /**
     * fields
     **/

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Embedded
    private Address homeAddress;

    @ElementCollection()
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

//    @ElementCollection()
//    @CollectionTable(name="ADDRESS", joinColumns = @JoinColumn(name="MEMBER_ID"))
//    private List<Address> addressHistory = new ArrayList<>();

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressEntities = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY) // Member 엔티티 조회시 프록시로 조회됨.
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Embedded
    private Period period;


    @Embedded
    @AttributeOverrides(
            {@AttributeOverride(name = "city", column = @Column(name = "WORK_CITY")),
                    @AttributeOverride(name = "street", column = @Column(name = "WORK_STREET")),
                    @AttributeOverride(name = "zipCode", column = @Column(name = "WORK_ZIPCODE"))
            })
    private Address workAddress;


    /**
     * getter setter
     **/
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


    public void setTeam(Team team) {
        this.team = team;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }


    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }


    public List<AddressEntity> getAddressEntities() {
        return addressEntities;
    }

    public void setAddressEntities(List<AddressEntity> addressEntities) {
        this.addressEntities = addressEntities;
    }
}

