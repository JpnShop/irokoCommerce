package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Notice;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.properties.Brand;
import java.time.LocalDateTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ResProduct {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        private String productName;
        private int price;
        private Brand brand;
        private int count;
        private String category;

        public static Response of(Product product) {
            return Response.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .brand(product.getBrand())
                .count(product.getCount())
                .category(product.getCategory())
                .build();
        }
    }
}

