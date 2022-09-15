package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Review;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReqReview {

    private Long id;
    private Member member;
    private Product product;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    public Review toEntity() {
        return new Review(member, product, title, content, createdDate);
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
