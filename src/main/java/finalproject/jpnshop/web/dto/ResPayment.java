package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Payment;
import finalproject.jpnshop.biz.domain.properties.PayMethod;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ResPayment {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class Response {

        private Long productId;
        private Integer count;
        private PayMethod payMethod;
        private Integer price;

        public static Response of(Payment payment) {
            return Response.builder()
                .productId(payment.getProduct().getId())
                .count(payment.getCount())
                .payMethod(payment.getPayMethod())
                .price(payment.getProduct().getPrice())
                .build();
        }
    }

}
