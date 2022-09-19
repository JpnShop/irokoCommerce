package finalproject.jpnshop.biz.repository;

import finalproject.jpnshop.biz.domain.Cart;
import finalproject.jpnshop.biz.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {


    Cart findByMember(Optional<Member> member);
}
