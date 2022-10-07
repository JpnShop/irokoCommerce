package finalproject.jpnshop.biz.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Type {

    @Id
    @Column(name = "type_name")
    private String name;

    private String imgSrc;

    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name = "men_category")
    private Set<String> men = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name = "women_category")
    private Set<String> women = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "subCategory_name")
    private SubCategory subCategory;

    public void setSubCategory(SubCategory subCategory) {
        if(this.getSubCategory() != null) {
            this.subCategory.getTypes().remove(this);
        }
        this.subCategory = subCategory;
        subCategory.getTypes().add(this);
    }

}

