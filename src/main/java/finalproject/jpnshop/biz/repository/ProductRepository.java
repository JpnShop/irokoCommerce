package finalproject.jpnshop.biz.repository;

import finalproject.jpnshop.biz.domain.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(" select p from Product p where p.id in :ids")
    List<Product> findByIds(@Param("ids") List<Long> ids);
}
