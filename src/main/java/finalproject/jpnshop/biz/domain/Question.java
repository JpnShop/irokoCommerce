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
import lombok.Getter;

//TODO
@Entity
@Getter
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

    private LocalDateTime createdDate;

    protected Question() {
    }

    public Question(Long id, Member member, Product product, String title, String content,
        LocalDateTime createdDate) {
        this.id = id;
        this.member = member;
        this.product = product;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}
