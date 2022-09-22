package finalproject.jpnshop.biz.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "CART")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Cart extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;
    @OneToOne
    private Member member;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private final List<CartItem> cartItems = new ArrayList<>();

    @Builder
    public Cart(Member member) {
        this.member = member;
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
        if (cartItem.getCart() != this) {
            cartItem.setCart(this);
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
            "id=" + id +
            ", member=" + member +
            ", cartItems=" + cartItems +
            '}';
    }
}
