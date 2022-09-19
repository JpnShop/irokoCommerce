package finalproject.jpnshop.biz.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

//TODO
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String title;

    @Lob
    private String content;

    @OneToOne
    private Answer answer;

    @Column(name = "createdAt")
    @CreatedDate
    private LocalDateTime createdDate;

    private String answerStatus;
    private String privateYn;

    private String password;

    public Question(Member member, Product product, String title, String content, LocalDateTime createdDate, String privateYn, String password) {
        this.member=member;
        this.product=product;
        this.title=title;
        this.content=content;
        this.createdDate=createdDate;
        this.answerStatus ="미답변";
        this.privateYn=privateYn;
        this.password=password;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void setAnswerStatus(String status) {
        this.answerStatus =status;
    }
}
