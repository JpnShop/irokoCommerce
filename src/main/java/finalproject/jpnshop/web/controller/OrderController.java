package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.service.DeliveryInfoService;
import finalproject.jpnshop.biz.service.MemberService;
import finalproject.jpnshop.biz.service.OrderService;
import finalproject.jpnshop.biz.service.ProductService;
import finalproject.jpnshop.web.dto.ResDeliveryInfo;
import finalproject.jpnshop.web.dto.ResMember;
import finalproject.jpnshop.web.dto.ResOrder;
import finalproject.jpnshop.web.dto.ResOrder.Response;
import finalproject.jpnshop.web.dto.ResProduct;
import finalproject.jpnshop.web.dto.ResponseWrapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ProductService productService;
    private final DeliveryInfoService deliveryInfoService;


    @GetMapping("/{deliveryId}")
    public ResponseEntity<ResponseWrapper> getOrder(@PathVariable Long deliveryId, @RequestParam String username) {

        ResMember.Response members = memberService.getMember(username);
        List<ResProduct.Response> products = productService.findProducts();
        ResDeliveryInfo.Response deliverys = deliveryInfoService.getDelivery(deliveryId);

        return ResponseEntity.ok(new ResponseWrapper(members, products, deliverys));
    }

    @PostMapping("/{memberId}")
    public ResponseEntity<ResOrder.Response> createOrder(@PathVariable Long memberId, @RequestParam Long productId, @RequestParam Long deliveryId, @RequestParam Integer count) {
        Long order = orderService.createOrder(memberId, productId, deliveryId, count);
        Response orders = orderService.findOrder(order);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orderList")
    public ResponseEntity<List<ResOrder.Response>> orderList() {
        List<ResOrder.Response> orders = orderService.findOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<ResOrder.Response> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
