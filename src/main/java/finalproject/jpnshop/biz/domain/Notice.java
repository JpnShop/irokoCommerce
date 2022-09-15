package finalproject.jpnshop.biz.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.Getter;

//TODO
@Entity
@Getter
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @ManyToOne
    private Member member;

    private String title;

    @Lob
    private String content;

    private LocalDateTime createdDate;

    protected Notice() {
    }

    public Notice(Long id, Member member, String title, String content, LocalDateTime createdDate) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}
