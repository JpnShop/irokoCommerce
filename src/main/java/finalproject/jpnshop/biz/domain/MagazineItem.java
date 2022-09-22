package finalproject.jpnshop.biz.domain;

import static javax.persistence.FetchType.LAZY;

import finalproject.jpnshop.biz.service.MagazineService;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class MagazineItem extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "magazine_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="magazine_id")
    private Magazine magazine;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    protected MagazineItem() {
    }

    protected MagazineItem(Long id, Magazine magazine, Product product) {
        this.id = id;
        this.magazine = magazine;
        this.product = product;
    }

    public void setMagazine(Magazine magazine) {
        if (this.magazine != null) {
            this.magazine.getMagazineItems().remove(this);
        }
        this.magazine = magazine;
        if (!this.magazine.getMagazineItems().contains(this)) {
            this.magazine.addMagazineItem(this);
        }
    }

    public void setProduct(Product product) {
        if (this.product != null) {
            this.product.getOrderItems().remove(this);
        }
        this.product = product;
        if (!this.product.getOrderItems().contains(this)) {
            this.product.addMagazineItem(this);
        }
    }
}
