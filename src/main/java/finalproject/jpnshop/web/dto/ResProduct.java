package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.properties.Brand;
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
        private int stock;
        private String category;

        public static Response of(Product product) {
            return Response.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .brand(product.getBrand())
                .stock(product.getStock())
                .category(product.getCategory())
                .build();
        }
    }
}

