package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.domain.Review;
import finalproject.jpnshop.biz.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 전체 목록 조회
    @GetMapping
    public List<Review> getReviews(){
        return reviewService.getReviews();
    }

    //리뷰 단건 조회
    @GetMapping("/{reviewId}")
    public Review getReview(@PathVariable long reviewId){
        return reviewService.getReview(reviewId);
    }

    //상품별 리뷰 조회
    @GetMapping("/{productId}")
    public Review getReviewByProduct(@PathVariable long productId) {
        return reviewService.getReviewByProduct(productId);
    }

    //리뷰 등록
    @PostMapping
    public String insertReview(){
        return "";
    }

    //리뷰 수정
    @PutMapping
    public String updateReview(){
        return "";
    }

    //리뷰 삭제
    @DeleteMapping
    public String deleteReview(){
        return "";
    }

}
