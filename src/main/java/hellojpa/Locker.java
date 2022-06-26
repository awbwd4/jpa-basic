package hellojpa;

import javax.persistence.*;

@Entity
public class Locker {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    //일대일 관계에서 읽기 전용.
    @OneToOne(mappedBy = "locker")
    private Member member;


}
