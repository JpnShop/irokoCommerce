package finalproject.jpnshop.biz.domain;

import finalproject.jpnshop.biz.domain.properties.Coupon;
import finalproject.jpnshop.biz.domain.properties.PayMethod;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Payment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    private Coupon coupon;

    private PayMethod payMethod;
    //TODO
    @OneToOne
    private Order order;

}
