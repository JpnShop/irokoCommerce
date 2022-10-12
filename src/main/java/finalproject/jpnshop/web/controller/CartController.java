package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.service.CartService;
import finalproject.jpnshop.util.SecurityUtil;
import finalproject.jpnshop.web.dto.ResCartItem.Response;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<Response> getCarts(){
        long memberId = SecurityUtil.getCurrentMemberId();
        return cartService.getCarts(memberId);
    }

    @PostMapping
    public String insertCart(@RequestBody Map<String,Long> map){
        long memberId = SecurityUtil.getCurrentMemberId();
        long productId = map.get("product_id");
        long countNum = map.get("count");
        cartService.insertCart(memberId, productId, (int) countNum);
        return "장바구니에 상품을 담았습니다.";
    }

    @PutMapping
    public String updateCount(@RequestBody Map<String,Long> map){
        long memberId = SecurityUtil.getCurrentMemberId();
        long productId = map.get("product_id");
        long countNum = map.get("count");
        cartService.updateCount(memberId, productId, (int) countNum);
        return "상품 개수가 수정되었습니다.";
    }

    @DeleteMapping("/empty")
    public String deleteAllCart(){
        long memberId = SecurityUtil.getCurrentMemberId();
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
