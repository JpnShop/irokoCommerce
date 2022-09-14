package finalproject.jpnshop.biz.repository;

import finalproject.jpnshop.biz.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
