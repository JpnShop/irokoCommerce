package finalproject.jpnshop.biz.domain;

import static javax.persistence.CascadeType.ALL;

import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Product extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String brand;
    @Column(name = "brandImg")
    private String brandImg;

    private String brandKo;
    private String tags;

    private String detailList;

    private String detailThumbList;

    private int price;

    private String productName;

    private String productOption;

    private int sale;

    private int stock; //재고

    private String summary;

    private String thumbnail;

    @OneToMany(mappedBy = "product", cascade = ALL)
    private List<CartItem> cartItems = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = ALL)
    private List<FavoriteItem> favoriteItems = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = ALL)
    private List<MagazineItem> magazineItems = new ArrayList<>();

    public void setProductName(String productName) {
    }

    public void setPrice(int price) {
    }

    public void setStock(int stock) {
    }

    public void addStock(int stock) {
        this.stock += stock;
    }

    public void removeStock(int stock) {
        int restStock = this.stock - stock;
        if (restStock < 0) {
            throw new CustomException(ErrorCode.STOCK_EMPTY);
        }
        this.stock = restStock;
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

    public void addMagazineItem(MagazineItem magazineItem) {
        this.magazineItems.add(magazineItem);
        if (magazineItem.getProduct() != this) {
            magazineItem.setProduct(this);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + id +
            ", brand='" + brand + '\'' +
            ", brandImg='" + brandImg + '\'' +
            ", brandKo='" + brandKo + '\'' +
            ", tags='" + tags + '\'' +
            ", detailList='" + detailList + '\'' +
            ", detailThumbList='" + detailThumbList + '\'' +
            ", price=" + price +
            ", productName='" + productName + '\'' +
            ", productOption='" + productOption + '\'' +
            ", sale=" + sale +
            ", stock=" + stock +
            ", summary='" + summary + '\'' +
            ", thumbnail='" + thumbnail + '\'' +
            ", cartItems=" + cartItems +
            '}';
    }
}