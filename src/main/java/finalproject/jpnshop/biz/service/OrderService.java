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
import finalproject.jpnshop.web.dto.ResOrder;
import finalproject.jpnshop.web.dto.ResOrder.Response;
import java.util.ArrayList;
import java.util.List;
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
    public Long createOrder(Long memberId, Long productId, Integer count) {

        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setAddress(deliveryInfo.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(product, product.getPrice(), count);

        Order order = Order.createOrder(member, deliveryInfo, orderItem);

        orderRepository.save(order);

        return order.getId();

    }

    public ResOrder.Response findOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
            () -> new CustomException(ErrorCode.ORDER_NOT_FOUND)
        );
        return ResOrder.Response.of(order);
    }

    @Transactional
    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(
            () -> new CustomException(ErrorCode.ORDER_NOT_FOUND)
        );

        order.cancel();
    }

    public List<ResOrder.Response> findOrders() {
        List<Order> orders = orderRepository.findAll();
        List<ResOrder.Response> orderList = new ArrayList<>();
        for (Order order : orders) {
            orderList.add(ResOrder.Response.of(order));
        }
        return orderList;
    }

}
