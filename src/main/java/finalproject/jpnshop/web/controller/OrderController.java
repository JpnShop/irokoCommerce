package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.service.MemberService;
import finalproject.jpnshop.biz.service.OrderService;
import finalproject.jpnshop.biz.service.ProductService;
import finalproject.jpnshop.web.dto.ResProduct.Response;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ProductService productService;

    @GetMapping
    public void order(Model model, String username) {
        List<Member> members = (List<Member>) memberService.getMember(username);
        List<Response> products = productService.findProducts();

        model.addAttribute("members", members);
        model.addAttribute("products", products);

    }

    @PostMapping
    public String order(Long memberId, Long productId, Long deliveryId, int count) {
        orderService.createOrder(memberId, productId, deliveryId, count);
        return "주문 신청이 완료 되었습니다.";
    }

    @GetMapping("/orderList")
    public String orderList() {
        orderService.findOrders();
        return "주문 목록 입니다.";
    }

    @PostMapping("/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "주문이 취소되었습니다.";
    }
}
