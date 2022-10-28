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
import finalproject.jpnshop.web.dto.ResCartItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public List<ResCartItem.Response> getCarts(long memberId) {
        Cart cart = getCart(memberId);
        List<CartItem> cartItems = cartItemRepository.findAllByCart(cart);
        List<ResCartItem.Response> cartItemList = new ArrayList<>();
        for (CartItem cartItem : cartItems){
            cartItemList.add(ResCartItem.Response.of(cartItem));
        }
        return cartItemList;
    }

    @Transactional
    public void insertCart(long memberId, long productId, int num) {
        Cart cart = getCart(memberId);
        log.info("cart : " + cart);

        Product product = getProduct(productId);

        CartItem cartItem = CartItem.builder()
            .cart(cart)
            .product(product)
            .count(num)
            .build();

        log.info("cart.getCartItems().size(): " + cart.getCartItems().size());

        for(int i=0; i<cart.getCartItems().size(); i++){
            CartItem target = cart.getCartItems().get(i);
            if(cartItem.getProduct().equals(target.getProduct())){
                target.addCount(cartItem.getCount());
                return;
            }
        }

        cart.addCartItem(cartItem);
        product.addCartItem(cartItem);
        cartItemRepository.save(cartItem);
    }


    @Transactional
    public void updateCount(long memberId, long productId, int num){
        Cart cart = getCart(memberId);
        Product product = getProduct(productId);
        CartItem cartItem = cartItemRepository.findByProductAndCart(product, cart);
        cartItem.setCount(num);
    }

    @Transactional
    public void deleteAllCart(long memberId) {
        Cart cart = getCart(memberId);
        cartItemRepository.deleteAllByCart(cart);
    }

    @Transactional
    public void deleteCartItem(long memberId, long productId) {
        Cart cart = getCart(memberId);
        Product product = getProduct(productId);
        CartItem cartItems = cartItemRepository.findByProductAndCart(product,cart);
        cartItemRepository.delete(cartItems);
    }

    private Cart getCart(long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        return cartRepository.findByMember(Optional.ofNullable(member));
    }

    private Product getProduct(long productId) {
        return productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
    }
}
