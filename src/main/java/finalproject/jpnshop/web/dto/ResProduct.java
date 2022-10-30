package finalproject.jpnshop.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import finalproject.jpnshop.biz.domain.Product;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
        private String brandKo;
        private List<String> tags;
        private List<String> detailList;
        private List<String> detailThumbList;
        private String productName;
        private String productOption;
        private Integer sale;
        private Integer price;
        private String brand;
        private Integer stock;
        private String thumbnail;

        public static Response simpleInfo(Product product) {
            return Response.builder()
                .productId(product.getId())
                .thumbnail(product.getThumbnail())
                .brand(product.getBrand())
                .productName(product.getProductName())
                .price(product.getPrice())
                .sale(product.getSale())
                .build();
        }

        public static Response of(Product product) {
            return Response.builder()
                .productId(product.getId())
                .brandImg(product.getBrandImg())
                .brandKo(product.getBrandKo())
                .thumbnail(product.getThumbnail())
                .sale(product.getSale())
                .tags(Arrays.stream(product.getTags().split(",")).map(String :: trim).collect(Collectors.toList()))
                .detailList(Arrays.stream(product.getDetailList().split(",")).map(String :: trim).collect(Collectors.toList()))
                .detailThumbList(Arrays.stream(product.getDetailThumbList().split(",")).map(String :: trim).collect(Collectors.toList()))
                .productName(product.getProductName())
                .price(product.getPrice())
                .brand(product.getBrand())
                .stock(product.getStock())
                .build();
        }
    }
}

