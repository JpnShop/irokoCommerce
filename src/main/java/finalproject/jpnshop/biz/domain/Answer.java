package finalproject.jpnshop.biz.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Lob
    private String content;

    @Column(name = "createdAt")
    @CreatedDate
    private LocalDate createdDate;

    @Override
    public String toString() {
        return "Answer{" +
            "id=" + id +
            ", member=" + member +
            ", content='" + content + '\'' +
            ", createdDate=" + createdDate +
            '}';
    }

    public void setContent(String content) {
        this.content=content;
    }
}
