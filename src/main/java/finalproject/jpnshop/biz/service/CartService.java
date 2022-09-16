package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Cart;
import finalproject.jpnshop.biz.domain.CartItem;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.CartItemRepository;
import finalproject.jpnshop.biz.repository.CartRepository;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.biz.repository.ProductRepository;
import finalproject.jpnshop.web.dto.ResProduct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public List<ResProduct.Response> getCarts(long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Cart cart = cartRepository.findByMember(Optional.ofNullable(member));
        List<CartItem> cartItems = cartItemRepository.findAllByCart(cart);
        List<ResProduct.Response> cartItemList = new ArrayList<>();
        for (CartItem cartItem : cartItems){
            cartItemList.add(ResProduct.Response.of(cartItem.getProduct()));
        }
        return cartItemList;
    }

    @Transactional
    public ResProduct.Response insertCart(long memberId, long productId, int num) {
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Cart cart = cartRepository.findByMember(Optional.ofNullable(member));
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        CartItem cartItem = CartItem.builder()
            .cart(cart)
            .product(product)
            .count(num)
            .build();
        cart.getCartItems().forEach(cartItem1 -> {
            if(cartItem1.getProduct().equals(product)){
                cartItem.setCount(cartItem1.getCount());
            }
        });

        cart.addCartItem(cartItem);
        product.addCartItem(cartItem);
        cartItemRepository.save(cartItem);
        return ResProduct.Response.of(product);
    }
    @Transactional
    public void deleteAllCart(long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Cart cart = cartRepository.findByMember(Optional.ofNullable(member));
        cartItemRepository.deleteAllByCart(cart);
    }

    @Transactional
    public void deleteCartItem(long memberId, long productId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Cart cart = cartRepository.findByMember(Optional.ofNullable(member));
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        List<CartItem> cartItems = cartItemRepository.findAllByProductAndCart(product,cart);
        cartItemRepository.deleteAll(cartItems);
    }

}
