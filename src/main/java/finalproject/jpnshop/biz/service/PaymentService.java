package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Payment;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.domain.properties.PayMethod;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.PaymentRepository;
import finalproject.jpnshop.biz.repository.ProductRepository;
import finalproject.jpnshop.web.dto.ReqPayment;
import finalproject.jpnshop.web.dto.ReqTotalOrder;
import finalproject.jpnshop.web.dto.ReqTotalOrder.ReqOrder;
import finalproject.jpnshop.web.dto.ResPayment;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository;

    public ResPayment.Response getPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(
            () -> new CustomException(ErrorCode.PAYMENT_NOT_FOUND)
        );

        return ResPayment.Response.of(payment);
    }

    @Transactional
    public void createPayment(ReqTotalOrder reqTotalOrder) {
        List<Long> productsId = reqTotalOrder.getTotalOrders()
            .stream()
            .map(ReqOrder::getProductId)
            .collect(Collectors.toList());

        List<Integer> counts = reqTotalOrder.getTotalOrders()
            .stream()
            .map(ReqOrder::getCount)
            .collect(Collectors.toList());

        PayMethod payMethod = reqTotalOrder.getPayMethod();

        for (int i = 0; i < counts.size(); i++) {
            Product product = productRepository.findById(productsId.get(i))
                .orElseThrow(() -> new CustomException(ErrorCode.ORDER_NOT_FOUND));

            Payment payment = Payment.builder()
                .payMethod(payMethod)
                .count(counts.get(i))
                .product(product)
                .build();

            payment.getProduct().removeStock(payment.getCount());
            paymentRepository.save(payment);
        }
    }

    @Transactional
    public void cancelPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(
            () -> new CustomException(ErrorCode.PAYMENT_NOT_FOUND)
        );

        Product product = payment.getProduct();
        product.addStock(payment.getCount());
        productRepository.save(product);
    }
}
