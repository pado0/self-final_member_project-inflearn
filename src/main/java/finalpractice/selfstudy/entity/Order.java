package finalpractice.selfstudy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // memberid와 조인해서 멤버를 가져옴
    private Member member;

    // 연관된 객체도 영속으로 만들기 위한 cascade. parent와 child의 라이프사이클이 유사할때 사용
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) // Enumtype 표시
    private OrderStatus orderStatus;

    // 연관관계 편의 메소드 : 양방향 연관관계 객체에서 양쪽 객체를 모두 신경쓰기 위함 //
    // 한 번에 양방향 관계를 설정하기 위함 //
    // 1:다 , 다:1.. 등에서 어디에 구현해야하는지 규칙이 있을까?

    // 멤버 세팅 메소드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    // orderItems에 오더아이템 넣어주고, 오더아이템에 오더셋
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // delivery setting
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
