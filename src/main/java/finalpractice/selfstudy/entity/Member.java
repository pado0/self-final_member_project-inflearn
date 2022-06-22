package finalpractice.selfstudy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    // 값타입
    @Embedded
    private Address address;

    // Order쪽의 member와 매핑됨
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
