package finalproject.jpnshop.biz.repository;

import finalproject.jpnshop.biz.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    Member findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsById(Long memberId);
    boolean existsByEmail(String email);
}
