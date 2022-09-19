package finalproject.jpnshop.biz.repository;

import finalproject.jpnshop.biz.domain.Cart;
import finalproject.jpnshop.biz.domain.Favorite;
import finalproject.jpnshop.biz.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {


    Favorite findByMember(Optional<Member> member);
}
