package finalproject.jpnshop.biz.domain;

import finalproject.jpnshop.biz.domain.properties.PayMethod;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    private PayMethod payMethod;

    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    public Payment(PayMethod payMethod, Integer count, Product product) {
        this.payMethod = payMethod;
        this.count = count;
        this.product = product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }
}
