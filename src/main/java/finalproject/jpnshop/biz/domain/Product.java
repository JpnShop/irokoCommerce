package finalproject.jpnshop.biz.domain;

import finalproject.jpnshop.biz.domain.properties.Brand;
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
public class Product extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String productName;

    private int price;

    @Enumerated(EnumType.STRING)
    private Brand brand;

    private int count;

    private String category;

    @OneToMany(mappedBy = "product",cascade = ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = ALL)
    private List<FavoriteItem> favoriteItems = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addCartItem(CartItem cartItem){
        this.cartItems.add(cartItem);
        if(cartItem.getProduct()!=this){
            cartItem.setProduct(this);
        }
    }

    public void addFavoriteItem(FavoriteItem favoriteItem){
        this.favoriteItems.add(favoriteItem);
        if(favoriteItem.getProduct()!=this){
            favoriteItem.setProduct(this);
        }
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        if(orderItem.getProduct()!=this){
            orderItem.setProduct(this);
        }
    }

}
