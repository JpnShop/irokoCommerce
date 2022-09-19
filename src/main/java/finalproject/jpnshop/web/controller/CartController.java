package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.service.CartService;
import finalproject.jpnshop.web.dto.ResProduct.Response;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<Response> getFavorites(@RequestBody Map<String,Long> map){
        long memberId = map.get("member_id");
        return cartService.getCarts(memberId);
    }

    @PostMapping
    public String insertCart(@RequestBody Map<String,Long> map){
        long memberId = map.get("member_id");
        long productId = map.get("product_id");
        long countNum = map.get("count");
        cartService.insertCart(memberId, productId, (int) countNum);
        return "장바구니에 상품을 담았습니다.";
    }

    @DeleteMapping("/empty")
    public String deleteAllCart(@RequestBody Map<String,Long> map){
        long memberId = map.get("member_id");
        cartService.deleteAllCart(memberId);
        return "장바구니를 비웠습니다.";
    }

    @DeleteMapping
    public String deleteCartItem(@RequestBody Map<String,Long> map){
        long memberId = map.get("member_id");
        long productId = map.get("product_id");
        cartService.deleteCartItem(memberId, productId);
        return "장바구니에서 상품을 삭제했습니다.";
    }
}
