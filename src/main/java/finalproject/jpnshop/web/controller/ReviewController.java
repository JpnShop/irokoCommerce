package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.service.ReviewService;
import finalproject.jpnshop.web.dto.ReqReview;
import finalproject.jpnshop.web.dto.ResReview;
import finalproject.jpnshop.web.dto.ResReview.Response;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/customers/reviews")
    public List<ResReview.Response> getReviews() {
        return reviewService.getReviews();
    }

    @GetMapping("/customers/reviews/{reviewId}")
    public Response getReview(@PathVariable long reviewId) {
        return reviewService.getReview(reviewId);
    }

    //todo : ProductController로 기능 이동 필요
    @GetMapping("/products/{productId}/reviews")
    public ResReview.Response getReviewByProduct(@PathVariable long productId) {
        return reviewService.getReviewByProduct(productId);
    }

    @PostMapping(value = "/customers/reviews/{productId}")
    public String insertReview(@RequestBody ReqReview review,
        @PathVariable Long productId) {
        reviewService.insertReview(review, productId);
        return "리뷰가 등록되었습니다.";
    }

    @PutMapping("/customers/reviews")
    public String updateReview() {
        return "";
    }


    @DeleteMapping("/customers/reviews")
    public String deleteReview() {
        return "";
    }

}
