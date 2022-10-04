package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.service.FavoriteService;
import finalproject.jpnshop.util.SecurityUtil;
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
    public List<Response> getFavorites(){
        long memberId = SecurityUtil.getCurrentMemberId();
        return favoriteService.getFavorites(memberId);
    }

    @PostMapping
    public String insertFavorite(@RequestBody Map<String,Long> map){
        long memberId = SecurityUtil.getCurrentMemberId();
        long productId = map.get("product_id");
        favoriteService.insertFavorite(memberId, productId);
        return "관심상품 목록에 저장했습니다.";
    }

    @DeleteMapping("/empty")
    public String deleteAllFavorite(){
        long memberId = SecurityUtil.getCurrentMemberId();
        favoriteService.deleteAllFavorite(memberId);
        return "관심상품 목록을 비웠습니다.";
    }

    @DeleteMapping
    public String deleteFavoriteItem(long productId){
        long memberId = SecurityUtil.getCurrentMemberId();
        favoriteService.deleteFavoriteItem(memberId, productId);
        return "관심상품 목록에서 상품을 삭제했습니다.";
    }

    @DeleteMapping
    public String deleteFavoriteItems(List<Long> productId){
        favoriteService.deleteFavoriteItems(productId);
    }
}
