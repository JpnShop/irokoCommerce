package finalproject.jpnshop.biz.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Magazine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "magazine_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "magazine", cascade = CascadeType.ALL)
    private List<MagazineItem> magazineItems = new ArrayList<>();


    private String thumnail;
    private String title;
    private String content;

    @CreatedDate
    private LocalDate createdDate;

    public Magazine(Member member, List<MagazineItem> magazineItems, String thumnail, String title, String content, LocalDate createdDate) {
        this.member = member;
        this.magazineItems = magazineItems;
        this.thumnail = thumnail;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }


    public void addMagazineItem(MagazineItem magazineItem) {
        this.magazineItems.add(magazineItem);
        if (magazineItem.getMagazine() != this) {
            magazineItem.setMagazine(this);
        }
    }

    public void setMagazineItems(List<MagazineItem> magazineItems) {
        this.magazineItems = magazineItems;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
