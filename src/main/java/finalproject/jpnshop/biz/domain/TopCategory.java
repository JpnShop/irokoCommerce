//package finalproject.jpnshop.biz.domain;
//
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//public class TopCategory {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "topCategory_id")
//    private Long id;
//
//    @Column(name = "topCategory_name")
//    private String name;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id")
//    private Category category;
//
//    @OneToMany(mappedBy = "topCategory", cascade = CascadeType.ALL)
//    private List<SubCategory> subCategories = new ArrayList<>();
//
//    public void setCategory(Category category) {
//        if(this.category != null) {
//            this.category.getTopCategories().remove(this);
//        }
//        this.category = category;
//        category.getTopCategories().add(this);
//    }
//
//    public void addSubCategory(SubCategory subCategory) {
//        this.getSubCategories().add(subCategory);
//        if(subCategory.getTopCategory() != this) {
//            subCategory.setTopCategory(this);
//        }
//    }
//}
