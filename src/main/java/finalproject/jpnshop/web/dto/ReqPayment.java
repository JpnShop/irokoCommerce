package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Order;
import finalproject.jpnshop.biz.domain.Payment;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.properties.PayMethod;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReqPayment {

    private PayMethod payMethod;
    private Integer count;
    private Order order;
    private Product product;

    public Payment toEntity() {
        return Payment.builder()
            .payMethod(payMethod)
            .count(count)
            .product(product)
            .build();
    }
}
