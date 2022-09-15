package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Question;
import finalproject.jpnshop.biz.domain.Review;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ResQuestion {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        private Member member;
        private Product product;
        private String title;
        private String content;
        private String answer;
        private LocalDateTime createdDate;

        public static Response of(Question question) {
            return Response.builder()
                .member(question.getMember())
                .product(question.getProduct())
                .title(question.getTitle())
                .content(question.getContent())
                .answer(question.getAnswer())
                .createdDate(question.getCreatedDate())
                .build();
        }
    }
}
