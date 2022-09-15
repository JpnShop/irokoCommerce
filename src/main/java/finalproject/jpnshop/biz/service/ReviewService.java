package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Review;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.biz.repository.ProductRepository;
import finalproject.jpnshop.biz.repository.ReviewRepository;
import finalproject.jpnshop.web.dto.ReqReview;
import finalproject.jpnshop.web.dto.ResReview;
import finalproject.jpnshop.web.dto.ResReview.Response;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;


    public List<ResReview.Response> getReviews() {
        List<Review> reviews = reviewRepository.findAll();
        List<ResReview.Response> responseList = new ArrayList<>();
        for (Review review : reviews) {
            responseList.add(ResReview.Response.of(review));
        }
        return responseList;
    }

    public ResReview.Response getReviewByProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        return Response.of(reviewRepository.findByProduct(product));
    }


    public ResReview.Response getReview(long reviewId) {
        return Response.of(reviewRepository.findById(reviewId).orElseThrow(
            () -> new CustomException(ErrorCode.REVIEW_NOT_FOUND)));
    }

    //todo : 상품 당 1건 리뷰 중복체크 및 사진 저장
    public void insertReview(ReqReview reviewForm, Long productId) {
        reviewForm.setMember(memberRepository.findById(1L).orElseThrow(
        () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)));
        reviewForm.setProduct(productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND)));
        Review review = reviewForm.toEntity();
        reviewRepository.save(review);
    }
}
