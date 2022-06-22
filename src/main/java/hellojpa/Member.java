package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity//JPA가 관리하는 객체.
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
public class Member {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id; //int는 10억이 넘어가면 다시 0부터 시작됨 id 같은건 그냥 Long쓰는게 나음
    //IDENTITY : 기본키 생성을 DB에 위임. flush되는 sql을 보면 id 값이 null이다


    @Column(name = "name", nullable = false)//테이블 칼럼명
    private String username;

    public Member() {
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
