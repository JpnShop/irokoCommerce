package finalproject.jpnshop.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import finalproject.jpnshop.biz.domain.Product;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ResProduct {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {

        private Long productId;
        private String brandImg;
        private String tags;
        private List<String> detailList;
        private List<String> detailThumbList;
        private String productName;
        private String productOption;
        private int sale;
        private int price;
        private String brand;
        private int stock;
        private String thumbnail;

        public static Response simpleInfo(Product product) {
            return Response.builder()
                .productId(product.getId())
                .thumbnail(product.getThumbnail())
                .brand(product.getBrand())
                .productName(product.getProductName())
                .price(product.getPrice())
                .build();
        }

        public static Response of(Product product) {
            return Response.builder()
                .productId(product.getId())
                .brandImg(product.getBrandImg())
                .tags(product.getTags())
                .detailList(Arrays.asList(product.getDetailList().split(",")))
                .detailThumbList(Arrays.asList(product.getDetailThumbList().split(",")))
                .productName(product.getProductName())
                .price(product.getPrice())
                .brand(product.getBrand())
                .stock(product.getStock())
                .build();
        }
    }
}

