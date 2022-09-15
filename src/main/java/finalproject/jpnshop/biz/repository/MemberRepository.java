package finalproject.jpnshop.biz.repository;

import finalproject.jpnshop.biz.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
