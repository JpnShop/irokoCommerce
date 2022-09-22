package finalproject.jpnshop.biz.repository;

import finalproject.jpnshop.biz.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
