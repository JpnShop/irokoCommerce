package finalproject.jpnshop.biz.repository;

import finalproject.jpnshop.biz.domain.Payment;
import finalproject.jpnshop.biz.domain.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(" select p from Product p where p.id in :ids")
    List<Product> findByIds(@Param("ids") List<Long> ids);
    List<Product> findByTagsContaining(String tag);

    @Query(" select p from Product p where p.payments in :pays")
    List<Payment> findBy(@Param("pays") List<Long> pays);

    Optional<Product> findById(Long productId);
}
