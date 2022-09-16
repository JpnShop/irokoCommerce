package finalproject.jpnshop.biz.repository;

import finalproject.jpnshop.biz.domain.Favorite;
import finalproject.jpnshop.biz.domain.FavoriteItem;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteItemRepository extends JpaRepository<FavoriteItem,Long> {

    List<FavoriteItem> findAllByFavorite(Favorite favorite);

    void deleteAllByFavorite(Favorite favorite);

    List<FavoriteItem> findAllByProductAndFavorite(Product product, Favorite favorite);
}
