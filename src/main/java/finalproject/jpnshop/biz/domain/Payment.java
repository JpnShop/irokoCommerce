package finalproject.jpnshop.biz.domain;

import finalproject.jpnshop.biz.domain.properties.Coupon;
import finalproject.jpnshop.biz.domain.properties.PayMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseTime{

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
