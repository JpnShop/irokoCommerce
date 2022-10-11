//package finalproject.jpnshop.biz.domain;
//
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@Table(name = "categorys")
//public class Category {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "category_id")
//    private Long id;
//
//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//    private final List<TopCategory> topCategories = new ArrayList<>();
//
//    public void addTopCategory(TopCategory topCategory) {
//        this.getTopCategories().add(topCategory);
//        if(topCategory.getCategory() != this) {
//            topCategory.setCategory(this);
//        }
//    }
//}
