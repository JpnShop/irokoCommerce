package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Answer;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Question;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ResAnswer {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        private Member member;
        private Question question;
        private String content;
        private LocalDate createdDate;

        public static Response of(Answer answer) {
            return Response.builder()
                .member(answer.getMember())
                .content(answer.getContent())
                .question(answer.getQuestion())
                .createdDate(answer.getCreatedDate())
                .build();
        }
    }
}

