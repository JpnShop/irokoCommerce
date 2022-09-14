package finalproject.jpnshop.biz.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import finalproject.jpnshop.biz.domain.properties.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime applyDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private final List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Order(Member member){
        this.member=member;
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        if(orderItem.getOrder()!=this){
            orderItem.setOrder(this);
        }
    }
}
