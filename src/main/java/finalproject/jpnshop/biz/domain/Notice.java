package finalproject.jpnshop.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//TODO
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @ManyToOne
    private Member member;

}
