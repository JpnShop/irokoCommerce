package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Answer;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Question;
import finalproject.jpnshop.biz.domain.properties.QuestionType;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ReqQuestion {

    private Long id;
    private Member member;
    private Product product;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private String answerYn;
    private Boolean privateYn;
    private Answer answer;

    private int password;

    private QuestionType type;

    public Question toEntity() {
        return new Question(member, product, title, content, createdDate, privateYn, password,
            type);
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }


    @Override
    public String toString() {
        return "ReqQuestion{" +
            "id=" + id +
            ", member=" + member +
            ", product=" + product +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", createdDate=" + createdDate +
            ", answerYn='" + answerYn + '\'' +
            ", privateYn='" + privateYn + '\'' +
            ", answer=" + answer +
            ", password=" + password +
            ", type=" + type +
            '}';
    }
}
