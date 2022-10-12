package finalproject.jpnshop.biz.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subCategory_id")
    private Long id;

    @Column(name = "subCategory_name")
    private String name;

    @Column(name = "subCategory_imgSrc")
    private String imgSrc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topCategory_id")
    private TopCategory topCategory;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    private List<Sort> sortList = new ArrayList<>();

    public void setTopCategory(TopCategory topCategory) {
        if(this.topCategory != null) {
            this.getTopCategory().getSubCategories().remove(this);
        }
        this.topCategory = topCategory;
        topCategory.getSubCategories().add(this);
    }

    public void addType(Sort sort) {
        this.getSortList().add(sort);
        if(sort.getSubCategory() != this) {
            sort.setSubCategory(this);
        }
    }
}
