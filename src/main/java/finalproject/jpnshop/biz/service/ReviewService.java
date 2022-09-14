package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Review;
import finalproject.jpnshop.biz.repository.ProductRepository;
import finalproject.jpnshop.biz.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    //리뷰 전체조회
    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    //상품별 리뷰 조회
    public Review getReviewByProduct(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        return reviewRepository.findByProduct(product);
    }


}
