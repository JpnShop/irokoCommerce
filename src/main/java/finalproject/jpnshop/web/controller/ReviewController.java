package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.domain.Review;
import finalproject.jpnshop.biz.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @GetMapping
    public String getReview(){
        return "";
    }


    //상품별 리뷰 조회
    @GetMapping
    public Review getReviewByProduct(@RequestBody Map<String,Long> map) {
        return reviewService.getReviewByProduct(map.get("productId"));
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
