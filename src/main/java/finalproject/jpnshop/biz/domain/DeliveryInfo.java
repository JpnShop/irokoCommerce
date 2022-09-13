package finalproject.jpnshop.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

}
