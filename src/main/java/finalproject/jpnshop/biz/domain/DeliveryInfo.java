package finalproject.jpnshop.biz.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class DeliveryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_info_id")
    private Long id;
    @OneToOne
    private Order order;
    @Embedded
    private Address address;
    private String phoneNumber;
    private String receiverName;

    protected DeliveryInfo() {
    }

    public DeliveryInfo(Long id, Order order, Address address, String phoneNumber,
        String receiverName) {
        this.id = id;
        this.order = order;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.receiverName = receiverName;
    }

}
