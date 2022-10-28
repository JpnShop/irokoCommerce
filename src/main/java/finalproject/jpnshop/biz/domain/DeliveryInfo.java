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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
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

    private String firstName;

    private String lastName;

    private String firstFurigana;

    private String lastFurigana;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Builder
    public DeliveryInfo(Long id, Order order, Address address, String phoneNumber,
        String firstName, String lastName, String firstFurigana, String lastFurigana) {
        this.id = id;
        this.order = order;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.firstFurigana = firstFurigana;
        this.lastFurigana = lastFurigana;
    }
}
