package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.DeliveryInfo;
import finalproject.jpnshop.biz.domain.Order;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.DeliveryInfoRepository;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.biz.repository.OrderRepository;
import finalproject.jpnshop.biz.repository.ProductRepository;
import finalproject.jpnshop.util.SecurityUtil;
import finalproject.jpnshop.web.dto.ReqDeliveryInfo;
import finalproject.jpnshop.web.dto.ResDeliveryInfo;
import finalproject.jpnshop.web.dto.ResOrder;
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


    public ResDeliveryInfo.Response createOrderInfo(ReqDeliveryInfo reqDeliveryInfo) {
        DeliveryInfo deliveryInfo = reqDeliveryInfo.toEntity();
        return ResDeliveryInfo.Response.of(deliveryInfo);
    }

    @Transactional
    public ResOrder.Response cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(
            () -> new CustomException(ErrorCode.ORDER_NOT_FOUND)
        );

        return ResOrder.Response.of(order);
    }

    public List<ResOrder.Response> findOrders() {
        List<Order> orders = orderRepository.findAll();
        List<ResOrder.Response> orderList = new ArrayList<>();
        for (Order order : orders) {
            orderList.add(ResOrder.Response.of(order));
        }
        return orderList;
    }

    public boolean nonmember() {
        if (memberRepository.existsById(SecurityUtil.getCurrentMemberId())) {
            return true;
        }
        return false;
    }

}
