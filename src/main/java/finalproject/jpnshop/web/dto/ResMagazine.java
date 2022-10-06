package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Magazine;
import finalproject.jpnshop.biz.domain.MagazineItem;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Notice;
import finalproject.jpnshop.biz.domain.properties.Tag;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ResMagazine {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private long id;
        private Member member;
        private List<MagazineItem> magazineItems;
        private String thumnail;
        private Tag tag;
        private String title;
        private String content;
        private LocalDate createdDate;

        public static Response of(Magazine magazine) {
            return Response.builder()
                .id(magazine.getId())
                .member(magazine.getMember())
                .magazineItems(magazine.getMagazineItems())
                .thumnail(magazine.getThumnail())
                .tag(magazine.getTag())
                .title(magazine.getTitle())
                .content(magazine.getContent())
                .createdDate(magazine.getCreatedDate())
                .build();
        }
    }
}

