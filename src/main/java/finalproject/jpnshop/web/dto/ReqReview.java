package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Review;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReqReview {

    private Long id;
    private Member member;
    private Product product;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    public Review toEntity() {
        return new Review(null, member, product, title, content, createdDate);
    }
}
