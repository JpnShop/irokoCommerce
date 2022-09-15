package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.service.CartService;
import finalproject.jpnshop.web.dto.ResProduct;
import finalproject.jpnshop.web.dto.ResProduct.Response;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<Response> getFavorites(){
        return cartService.getFavorites();
    }
}
