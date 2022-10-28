package finalproject.jpnshop.biz.domain;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderItem extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    private int orderPrice;
    private int orderCount;
    @ManyToOne
    private Order order;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void setOrder(Order order) {
        if (this.order != null) {
            this.order.getOrderItems().remove(this);
        }
        this.order = order;
        if (!this.order.getOrderItems().contains(this)) {
            this.order.addOrderItem(this);
        }
    }

    public void setProduct(Product product) {
        if (this.product != null) {
            this.product.getOrderItems().remove(this);
        }
        this.product = product;
        if (!this.product.getOrderItems().contains(this)) {
            this.product.addOrderItem(this);
        }
    }

//    public void removeStock(Product product, Integer count) {
//        product.removeStock(count);
//    }

    public void addStock() {
        getProduct().addStock(orderCount);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getOrderCount();
    }
}
