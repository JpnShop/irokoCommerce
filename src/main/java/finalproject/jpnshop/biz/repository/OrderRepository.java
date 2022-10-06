package finalproject.jpnshop.biz.repository;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Order;
import finalproject.jpnshop.biz.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {


    Order findByMemberAndOrderItems(Member member, OrderItem orderItem);
}
