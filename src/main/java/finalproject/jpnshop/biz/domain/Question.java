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
public class Question extends CustomerSupport{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questions_id")
    private Long id;
}
