package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Favorite;
import finalproject.jpnshop.biz.domain.FavoriteItem;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.FavoriteItemRepository;
import finalproject.jpnshop.biz.repository.FavoriteRepository;
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
@Transactional(readOnly = true)
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final FavoriteItemRepository favoriteItemRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public List<ResProduct.Response> getFavorites(long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Favorite favorite = favoriteRepository.findByMember(Optional.ofNullable(member));
        List<FavoriteItem> favoriteItems = favoriteItemRepository.findAllByFavorite(favorite);
        List<ResProduct.Response> cartItemList = new ArrayList<>();
        for (FavoriteItem favoriteItem : favoriteItems){
            cartItemList.add(ResProduct.Response.of(favoriteItem.getProduct()));
        }
        return cartItemList;
    }

    @Transactional
    public void insertFavorite(long memberId, long productId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Favorite favorite = favoriteRepository.findByMember(Optional.ofNullable(member));
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        FavoriteItem favoriteItem = FavoriteItem.builder()
            .favorite(favorite)
            .product(product)
            .build();
        favorite.getFavoriteItems().forEach(favoriteItem1 -> {
            if(favoriteItem1.getProduct().equals(product)){
                throw new CustomException(ErrorCode.PRODUCT_EXIST);
            }
        });

        favorite.addFavoriteItem(favoriteItem);
        product.addFavoriteItem(favoriteItem);
        favoriteItemRepository.save(favoriteItem);
        ResProduct.Response.of(product);
    }
    @Transactional
    public void deleteAllFavorite(long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Favorite favorite = favoriteRepository.findByMember(Optional.ofNullable(member));
        favoriteItemRepository.deleteAllByFavorite(favorite);
    }

    @Transactional
    public void deleteFavoriteItem(long memberId, long productId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Favorite favorite = favoriteRepository.findByMember(Optional.ofNullable(member));
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        List<FavoriteItem> favoriteItems = favoriteItemRepository.findAllByProductAndFavorite(product,favorite);
        favoriteItemRepository.deleteAll(favoriteItems);
    }

}
