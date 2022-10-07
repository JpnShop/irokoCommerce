package finalproject.jpnshop.biz.repository;


import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review findByProduct(Product product);

    List<Review> findAllByProduct(Product product);

    List<Review> findByMember(Member member);

    Review findByMemberAndProduct(Member member, Product newReviewProduct);
}
