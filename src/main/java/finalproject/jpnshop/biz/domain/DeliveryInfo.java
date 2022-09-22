package finalproject.jpnshop.biz.domain;

import finalproject.jpnshop.biz.domain.properties.DeliveryStatus;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public void setAddress(Address address) {
        this.address = address;
    }


}
