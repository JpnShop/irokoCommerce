package finalproject.jpnshop.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    private int count;

    @ManyToOne
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    public void setOrder(Order order){
        if(this.order != null){
            this.order.getOrderItems().remove(this);
        }
        this.order = order;
        if(!this.order.getOrderItems().contains(this)){
            this.order.addOrderItem(this);
        }
    }

    public void setProduct(Product product){
        if(this.product != null){
            this.product.getOrderItems().remove(this);
        }
        this.product =product;
        if(!this.product.getOrderItems().contains(this)){
            this.product.addOrderItem(this);
        }
    }
}
