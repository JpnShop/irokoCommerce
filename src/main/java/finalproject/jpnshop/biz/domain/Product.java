package finalproject.jpnshop.biz.domain;

import static javax.persistence.CascadeType.ALL;

import finalproject.jpnshop.biz.domain.properties.Brand;
import finalproject.jpnshop.biz.domain.properties.Status;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class Product extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status sellStatus;

    private String summary;

    private String productName;

    private String productOption;

    private int price;

    private int point;

    @Enumerated(EnumType.STRING)
    private Brand brand;

    private int stock;

    private String category;

    private String detailImage;

    private String listImage;

    private String smallListImage;

    private String iconImage;

    @OneToMany(mappedBy = "product", cascade = ALL)
    private List<CartItem> cartItems = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = ALL)
    private List<FavoriteItem> favoriteItems = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    protected Product() {
    }

    public Product(Long id, String productName, int price, Brand brand, int stock, String category,
        List<CartItem> cartItems, List<FavoriteItem> favoriteItems, List<OrderItem> orderItems) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.brand = brand;
        this.stock = stock;
        this.category = category;
        this.cartItems = cartItems;
        this.favoriteItems = favoriteItems;
        this.orderItems = orderItems;
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
        if (cartItem.getProduct() != this) {
            cartItem.setProduct(this);
        }
    }

    public void addFavoriteItem(FavoriteItem favoriteItem) {
        this.favoriteItems.add(favoriteItem);
        if (favoriteItem.getProduct() != this) {
            favoriteItem.setProduct(this);
        }
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        if (orderItem.getProduct() != this) {
            orderItem.setProduct(this);
        }
    }

}
