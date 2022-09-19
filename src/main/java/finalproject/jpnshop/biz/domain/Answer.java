package finalproject.jpnshop.biz.domain;

import java.time.LocalDate;
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
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    private Question question;

    @Lob
    private String content;

    @Column(name = "createdAt")
    @CreatedDate
    private LocalDate createdDate;

    public Answer(Member member, Question question, String content, LocalDateTime createdDate) {
    }

    public Answer() {

    }

    public void setContent(String content) {
        this.content=content;
    }
}
