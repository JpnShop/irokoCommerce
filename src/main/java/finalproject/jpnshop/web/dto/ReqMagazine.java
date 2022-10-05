package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Magazine;
import finalproject.jpnshop.biz.domain.MagazineItem;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Review;
import finalproject.jpnshop.biz.domain.properties.Tag;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReqMagazine {

    private Long id;
    private Member member;

    private List<MagazineItem> magazineItems;
    private String thumnail;
    private String title;
    private String content;
    private LocalDate createdDate;

    private Tag tag;
    public Magazine toEntity() {
        return new Magazine(member, magazineItems, thumnail, title, content, createdDate, tag);
    }

    public void setMember(Member member) {
        this.member = member;
    }


}
