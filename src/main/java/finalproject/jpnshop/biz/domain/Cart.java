package finalproject.jpnshop.biz.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne
    private Member member;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonManagedReference
    private final List<CartItem> cartItems = new ArrayList<>();

    @Builder
    public Cart(Member member){
        this.member=member;
    }

    public void addCartItem(CartItem cartItem){
        this.cartItems.add(cartItem);
        if(cartItem.getCart()!=this){
            cartItem.setCart(this);
        }
    }

}
