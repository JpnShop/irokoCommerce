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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Favorite extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long id;

    @OneToOne
    private Member member;

    @OneToMany(mappedBy = "favorite", cascade = CascadeType.ALL)
    private List<FavoriteItem> favoriteItems = new ArrayList<>();

    @Builder
    public Favorite(Member member) {
        this.member = member;
    }

    public void addFavoriteItem(FavoriteItem favoriteItem) {
        this.favoriteItems.add(favoriteItem);
        if (favoriteItem.getFavorite() != this) {
            favoriteItem.setFavorite(this);
        }
    }
}
