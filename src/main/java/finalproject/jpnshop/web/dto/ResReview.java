package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Review;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ResReview {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private long id;
        private Member member;
        private Product product;
        private String title;
        private String content;
        private LocalDateTime createdDate;

        public static Response of(Review review) {
            return Response.builder()
                .id(review.getId())
                .member(review.getMember())
                .product(review.getProduct())
                .title(review.getTitle())
                .content(review.getContent())
                .createdDate(review.getCreatedDate())
                .build();
        }
    }
}

