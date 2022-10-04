package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Answer;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Question;
import finalproject.jpnshop.biz.domain.properties.QuestionType;
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
        private long id;
        private Member member;
        private Product product;
        private String title;
        private String content;
        private Answer answer;
        private String privateYn;
        private String answerYn;
        private int password;
        private LocalDateTime createdDate;
        private QuestionType type;

        public static Response of(Question question) {
            return Response.builder()
                .id(question.getId())
                .member(question.getMember())
                .product(question.getProduct())
                .title(question.getTitle())
                .content(question.getContent())
                .answer(question.getAnswer())
                .answerYn(question.getAnswerStatus())
                .privateYn(question.getPrivateYn())
                .password(question.getPassword())
                .createdDate(question.getCreatedDate())
                .type(question.getType())
                .build();
        }
    }
}

