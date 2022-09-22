package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.service.MemberService;
import finalproject.jpnshop.biz.service.OrderService;
import finalproject.jpnshop.biz.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ProductService productService;


}
