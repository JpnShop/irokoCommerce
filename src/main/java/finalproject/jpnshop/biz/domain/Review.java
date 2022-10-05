package finalproject.jpnshop.biz.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//TODO
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String title;

    private double star;
    @Lob
    private String content;

    @OneToMany(
        mappedBy = "review",
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        orphanRemoval = true
    )
    private List<Image> image = new ArrayList<>();

    private LocalDateTime createdDate;

    public Review(Member member, Product product, String title, String content, LocalDateTime createdDate, double star, List<Image> image) {
        this.member = member;
        this.product = product;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.star = star;
        this.image = image;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public void addImage(Image image) {
        this.image.add(image);
    }
}
