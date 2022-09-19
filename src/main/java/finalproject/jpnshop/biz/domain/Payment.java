package finalproject.jpnshop.biz.domain;

import finalproject.jpnshop.biz.domain.properties.Coupon;
import finalproject.jpnshop.biz.domain.properties.PayMethod;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
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

    protected Payment() {
    }

    public Payment(Long id, Coupon coupon, PayMethod payMethod, Order order) {
        this.id = id;
        this.coupon = coupon;
        this.payMethod = payMethod;
        this.order = order;
    }

}
