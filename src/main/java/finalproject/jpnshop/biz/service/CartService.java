package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Cart;
import finalproject.jpnshop.biz.domain.CartItem;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.CartItemRepository;
import finalproject.jpnshop.biz.repository.CartRepository;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.web.dto.ResProduct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;

    public List<ResProduct.Response> getFavorites() {
        Member member = memberRepository.findById(1L).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Cart cart = cartRepository.findByMember(Optional.ofNullable(member));
        List<CartItem> cartItems = cartItemRepository.findAllByCart(cart);
        List<ResProduct.Response> cartItemList = new ArrayList<>();
        for (CartItem cartItem : cartItems){
            cartItemList.add(ResProduct.Response.of(cartItem.getProduct()));
        }
        return cartItemList;
    }
}
