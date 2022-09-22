package finalproject.jpnshop.biz.repository;

import finalproject.jpnshop.biz.domain.Answer;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    List<Question> findAllByProduct(Product product);

    Question findByAnswer(Answer answer);

    List<Question> findAllByMember(Member member);
}
