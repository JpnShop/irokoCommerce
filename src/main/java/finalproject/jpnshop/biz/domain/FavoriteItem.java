package finalproject.jpnshop.biz.domain;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class FavoriteItem extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_item_id")
    private Long id;
    @ManyToOne
    private Favorite favorite;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    protected FavoriteItem() {
    }

    public FavoriteItem(Long id, Favorite favorite, Product product) {
        this.id = id;
        this.favorite = favorite;
        this.product = product;
    }

    public void setFavorite(Favorite favorite) {
        if (this.favorite != null) {
            this.favorite.getFavoriteItems().remove(this);
        }
        this.favorite = favorite;
        if (!this.favorite.getFavoriteItems().contains(this)) {
            this.favorite.addFavoriteItem(this);
        }
    }

    public void setProduct(Product product) {
        if (this.product != null) {
            this.product.getFavoriteItems().remove(this);
        }
        this.product = product;
        if (!this.product.getFavoriteItems().contains(this)) {
            this.product.addFavoriteItem(this);
        }
    }
}
