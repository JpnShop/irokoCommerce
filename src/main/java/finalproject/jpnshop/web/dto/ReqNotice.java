package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Notice;
import finalproject.jpnshop.biz.domain.Review;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ReqNotice {

    private Long id;
    private Member member;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    public Notice toEntity() {
        return new Notice(member, title, content, createdDate);
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "ReqNotice{" + "id=" + id + ", member=" + member + ", title='" + title + '\''
            + ", content='" + content + '\'' + ", createdDate=" + createdDate + '}';
    }
}
