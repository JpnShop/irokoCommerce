package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.domain.Review;
import finalproject.jpnshop.biz.service.ReviewService;
import finalproject.jpnshop.web.dto.ResReview;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 전체 목록 조회
    @GetMapping("/customers/reviews")
    public List<ResReview.Response> getReviews() {
        return reviewService.getReviews();
    }

    //리뷰 단건 조회
    @GetMapping("/customers/reviews/{reviewId}")
    public Review getReview(@PathVariable long reviewId) {
        return reviewService.getReview(reviewId);
    }

    //todo : ProductController로 기능 이동 필요
    //상품별 리뷰 조회
    @GetMapping("/products/{productId}/reviews")
    public Review getReviewByProduct(@PathVariable long productId) {
        return reviewService.getReviewByProduct(productId);
    }

    //리뷰 등록
    @PostMapping("/customers/reviews")
    public String insertReview() {
        return "";
    }

    //리뷰 수정
    @PutMapping("/customers/reviews")
    public String updateReview() {
        return "";
    }

    //리뷰 삭제
    @DeleteMapping("/customers/reviews")
    public String deleteReview() {
        return "";
    }

}
