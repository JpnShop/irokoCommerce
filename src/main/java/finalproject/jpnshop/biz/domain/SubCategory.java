package finalproject.jpnshop.biz.domain;

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
public class SubCategory {

    @Id
    @Column(name = "subCategory_name")
    private String name;

    private String imgSrc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topCategory_name")
    private TopCategory topCategory;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    private List<Type> types = new ArrayList<>();

    public void setTopCategory(TopCategory topCategory) {
        if(this.topCategory != null) {
            this.getTopCategory().getSubCategories().remove(this);
        }
        this.topCategory = topCategory;
        topCategory.getSubCategories().add(this);
    }

    public void addType(Type type) {
        this.getTypes().add(type);
        if(type.getSubCategory() != this) {
            type.setSubCategory(this);
        }
    }
}
