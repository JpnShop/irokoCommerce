package finalproject.jpnshop.biz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terms_id")
    private Long id;

    private String ServiceContent;
    private String PersonalContent;

    @Builder
    public Terms(Long id, String ServiceContent, String PersonalContent) {
        this.id = id;
        this.ServiceContent = ServiceContent;
        this.PersonalContent = PersonalContent;
    }

}
