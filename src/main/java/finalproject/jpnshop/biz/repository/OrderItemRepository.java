package finalproject.jpnshop.biz.repository;

import finalproject.jpnshop.biz.domain.OrderItem;
import finalproject.jpnshop.biz.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    OrderItem findByProduct(Product product);
}
