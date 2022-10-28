package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.CartItem;
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
        private long productId;
        private int sale;
        private int stock;
        private String thumbnail;
        private String productName;
        private int price;
        private String brand;
        private String category;
        private int count;

        public static Response of(CartItem cartItem) {
            return Response.builder()
                .id(cartItem.getId())
                .productId(cartItem.getProduct().getId())
                .sale(cartItem.getProduct().getSale())
                .stock(cartItem.getProduct().getStock())
                .thumbnail(cartItem.getProduct().getThumbnail())
                .productName(cartItem.getProduct().getProductName())
                .price(cartItem.getProduct().getPrice())
                .brand(cartItem.getProduct().getBrand())
                .category(cartItem.getProduct().getTags())
                .count(cartItem.getCount())
                .build();
        }
    }
}

