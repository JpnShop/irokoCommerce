package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Answer;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Question;
import finalproject.jpnshop.biz.domain.Review;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ReqAnswer {

    private Long id;
    private Member member;
    private Question question;
    private String content;
    private LocalDate createdDate;

    public Answer toEntity() {
        return new Answer(member, content, createdDate);
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setQuestion(Question question){
        this.question= question;
    }

    @Override
    public String toString() {
        return "ReqAnswer{" +
            "id=" + id +
            ", member=" + member +
            ", question=" + question +
            ", content='" + content + '\'' +
            ", createdDate=" + createdDate +
            '}';
    }
}
