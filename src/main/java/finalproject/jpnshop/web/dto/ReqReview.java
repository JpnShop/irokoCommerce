package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Image;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Review;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class ReqReview {

    private Long id;
    private Member member;
    private Product product;
    private double star;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    private List<Image> image;


    public Review toEntity() {
        return new Review(member, product, title, content, createdDate, star, image);
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ReqReview{" +
            "id=" + id +
            ", member=" + member +
            ", product=" + product +
            ", star=" + star +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", createdDate=" + createdDate +
            '}';
    }
}
