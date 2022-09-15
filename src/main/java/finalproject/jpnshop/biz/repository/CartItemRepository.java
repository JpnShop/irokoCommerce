package finalproject.jpnshop.biz.repository;

import finalproject.jpnshop.biz.domain.Cart;
import finalproject.jpnshop.biz.domain.CartItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    List<CartItem> findAllByCart(Cart cart);
}
