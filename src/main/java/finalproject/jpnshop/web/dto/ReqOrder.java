package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.DeliveryInfo;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Order;
import finalproject.jpnshop.biz.domain.OrderItem;
import finalproject.jpnshop.biz.domain.properties.Status;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ReqOrder {

    private Long id;
    private Status status;
    private Member member;
    private DeliveryInfo deliveryInfo;

    private List<OrderItem> orderItem = new ArrayList<>();

    public Order toEntity() {
        return new Order(status, member, deliveryInfo, orderItem);
    }

    @Override
    public String toString() {
        return "ReqOrder{" +
            "id=" + id +
            ", status=" + status +
            ", member=" + member +
            ", deliveryInfo=" + deliveryInfo +
            '}';
    }
}
