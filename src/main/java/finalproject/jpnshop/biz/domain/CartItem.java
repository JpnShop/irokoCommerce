package finalproject.jpnshop.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    private int count;
    @ManyToOne
    private Cart cart;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    public void setCart(Cart cart){
        if(this.cart != null){
            this.cart.getCartItems().remove(this);
        }
        this.cart =cart;
        if(!this.cart.getCartItems().contains(this)){
            this.cart.addCartItem(this);
        }
    }

    public void setProduct(Product product){
        if(this.product != null){
            this.product.getCartItems().remove(this);
        }
        this.product =product;
        if(!this.product.getCartItems().contains(this)){
            this.product.addCartItem(this);
        }
    }
}
