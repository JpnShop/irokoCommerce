package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Member;
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
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;


    public List<ResReview.Response> getReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return getResponses(reviews);
    }

    public List<ResReview.Response>  getReviewByProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        List<Review> reviewList = reviewRepository.findAllByProduct(product);
        return getResponses(reviewList);
    }

    public List<Response> getReviewsByMember(long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        List<Review> reviews = reviewRepository.findByMember(member);
        return getResponses(reviews);
    }

    public ResReview.Response getReview(long reviewId) {
        return Response.of(reviewRepository.findById(reviewId).orElseThrow(
            () -> new CustomException(ErrorCode.REVIEW_NOT_FOUND)));
    }

    //todo : 상품 당 1건 리뷰 중복체크 및 사진 저장, 로그인한 회원으로 멤버 저장
    @Transactional
    public void insertReview(ReqReview reviewForm, Long productId) {
        reviewForm.setMember(memberRepository.findById(1L).orElseThrow(
        () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)));
        reviewForm.setProduct(productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND)));
        Review review = reviewForm.toEntity();
        reviewRepository.save(review);
    }

    @Transactional
    public void updateReview(ReqReview reviewForm, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
            () -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));
        review.setTitle(reviewForm.getTitle());
        review.setContent(reviewForm.getContent());
        reviewRepository.save(review); //todo: 왜 save를 해야 수정이 반영되는지?
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
            () -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));
        reviewRepository.delete(review);
    }

    private List<Response> getResponses(List<Review> reviews) {
        List<Response> responseList = new ArrayList<>();
        for (Review review : reviews) {
            responseList.add(Response.of(review));
        }
        return responseList;
    }
}
