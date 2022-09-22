package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.DeliveryInfo;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Order;
import finalproject.jpnshop.biz.domain.OrderItem;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.DeliveryInfoRepository;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.biz.repository.OrderRepository;
import finalproject.jpnshop.biz.repository.ProductRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    private final DeliveryInfoRepository deliveryInfoRepository;

    @Transactional
    public Long createOrder(Long memberId, Long productId, Long deliveryInfoId ,int count) {

        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        DeliveryInfo deliveryInfo = deliveryInfoRepository.findById(deliveryInfoId).orElseThrow(
            () -> new CustomException(ErrorCode.DELIVERY_NOT_FOUND));
        deliveryInfo.setAddress(deliveryInfo.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(product, product.getPrice(), count);

        Order order = Order.createOrder(member, deliveryInfo, orderItem);

        orderRepository.save(order);

        return order.getId();

    }

    @Transactional
    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(
            () -> new CustomException(ErrorCode.ORDER_NOT_FOUND)
        );

        order.cancel();
    }

}
