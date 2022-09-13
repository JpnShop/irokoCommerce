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
public class FavoriteItem extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_item_id")
    private Long id;

    @ManyToOne
    private Favorite favorite;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    public void setFavorite(Favorite favorite){
        if(this.favorite != null){
            this.favorite.getFavoriteItems().remove(this);
        }
        this.favorite = favorite;
        if(!this.favorite.getFavoriteItems().contains(this)){
            this.favorite.addFavoriteItem(this);
        }
    }

    public void setProduct(Product product){
        if(this.product != null){
            this.product.getFavoriteItems().remove(this);
        }
        this.product =product;
        if(!this.product.getFavoriteItems().contains(this)){
            this.product.addFavoriteItem(this);
        }
    }
}
