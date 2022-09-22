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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    private int count;
    @ManyToOne
    private Cart cart;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public CartItem(Long id, int count, Cart cart, Product product) {
        this.id = id;
        this.count = count;
        this.cart = cart;
        this.product = product;
    }

    public void setCart(Cart cart) {
        if (this.cart != null) {
            this.cart.getCartItems().remove(this);
        }
        this.cart = cart;
        if (!this.cart.getCartItems().contains(this)) {
            this.cart.addCartItem(this);
        }
    }

    public void setProduct(Product product) {
        if (this.product != null) {
            this.product.getCartItems().remove(this);
        }
        this.product = product;
        if (!this.product.getCartItems().contains(this)) {
            this.product.addCartItem(this);
        }
    }

    public void setCount(int num) {
        this.count += num;
    }
}
