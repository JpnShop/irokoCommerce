package finalproject.jpnshop.biz.domain;

import finalproject.jpnshop.biz.domain.properties.DeliveryStatus;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.domain.properties.Status;
import finalproject.jpnshop.biz.exception.CustomException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTime {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DeliveryInfo deliveryInfo;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private final List<OrderItem> orderItems = new ArrayList<>();

    public Order(Status status, Member member, DeliveryInfo deliveryInfo, List<OrderItem> orderItems) {
        super();
    }


    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        if (orderItem.getOrder() != this) {
            orderItem.setOrder(this);
        }
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime now) {
    }

    private void setDeliveryInfo(DeliveryInfo deliveryInfo) {
    }

//    public static Order createOrder(Member member, DeliveryInfo deliveryInfo, OrderItem... orderItems) {
//        Order order = new Order();
//        order.setMember(member);
//        order.setDeliveryInfo(deliveryInfo);
//        for (OrderItem orderItem : orderItems) {
//            order.addOrderItem(orderItem);
//        }
//        order.setStatus(Status.ORDER);
//        order.setCreatedAt(LocalDateTime.now());
//        return order;
//    }

//    public void cancel(OrderItem... orderItems) {
//        if (deliveryInfo.getStatus() == DeliveryStatus.COMPLETE) {
//            throw new CustomException(ErrorCode.ORDER_COMP_ERROR);
//        }
//        this.setStatus(Status.CANCEL);
//        for (OrderItem orderItem : orderItems) {
//            orderItem.cancel();
//        }
//    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    private String orderNum;

    @PrePersist
    void orderNum() {
        orderNum =
            getCreatedAt().toString() + member.getAddress().getZipcode() + member.lastPhoneNum();
    }

}
