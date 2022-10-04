package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Notice;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Review;
import finalproject.jpnshop.web.dto.ResReview.Response;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ResNotice {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private long id;
        private Member member;
        private String title;
        private String content;
        private LocalDateTime createdDate;

        public static Response of(Notice notice) {
            return Response.builder()
                .id(notice.getId())
                .member(notice.getMember())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdDate(notice.getCreatedDate())
                .build();
        }
    }
}

