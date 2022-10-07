package finalproject.jpnshop.biz.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Sort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sort_id")
    private Long id;

    @Column(name = "sort_name")
    private String name;

    @Column(name = "sort_imgSrc")
    private String imgSrc;

    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name = "men_category")
    private Set<String> men = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name = "women_category")
    private Set<String> women = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "subCategory_id")
    private SubCategory subCategory;

    public void setSubCategory(SubCategory subCategory) {
        if(this.getSubCategory() != null) {
            this.subCategory.getSortList().remove(this);
        }
        this.subCategory = subCategory;
        subCategory.getSortList().add(this);
    }

}

