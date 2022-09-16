package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.service.CartService;
import finalproject.jpnshop.biz.service.FavoriteService;
import finalproject.jpnshop.web.dto.ResProduct.Response;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping
    public List<Response> getFavorites(@RequestBody Map<String,Long> map){
        long memberId = map.get("member_id");
        return favoriteService.getFavorites(memberId);
    }

    @PostMapping
    public String insertFavorite(@RequestBody Map<String,Long> map){
        long memberId = map.get("member_id");
        long productId = map.get("product_id");
        favoriteService.insertFavorite(memberId, productId);
        return "관심상품 목록에 저장했습니다.";
    }

    @DeleteMapping("/empty")
    public String deleteAllFavorite(@RequestBody Map<String,Long> map){
        long memberId = map.get("member_id");
        favoriteService.deleteAllFavorite(memberId);
        return "관심상품 목록을 비웠습니다.";
    }

    @DeleteMapping
    public String deleteFavoriteItem(@RequestBody Map<String,Long> map){
        long memberId = map.get("member_id");
        long productId = map.get("product_id");
        favoriteService.deleteFavoriteItem(memberId, productId);
        return "관심상품 목록에서 상품을 삭제했습니다.";
    }
}
