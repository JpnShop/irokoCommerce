package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.CartItem;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.properties.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ResCartItem {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private long id;
        private String productName;
        private int price;
        private Brand brand;
        private String category;
        private int count;

        public static Response of(CartItem cartItem) {
            return Response.builder()
                .id(cartItem.getId())
                .productName(cartItem.getProduct().getProductName())
                .price(cartItem.getProduct().getPrice())
                .brand(cartItem.getProduct().getBrand())
                .category(cartItem.getProduct().getCategory())
                .count(cartItem.getCount())
                .build();
        }
    }
}

