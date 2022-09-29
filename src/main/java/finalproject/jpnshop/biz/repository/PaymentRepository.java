package finalproject.jpnshop.biz.repository;

import finalproject.jpnshop.biz.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
