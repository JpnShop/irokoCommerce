package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.component.FileHandler;
import finalproject.jpnshop.biz.domain.Image;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Order;
import finalproject.jpnshop.biz.domain.OrderItem;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.Review;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.ImageRepository;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.biz.repository.OrderItemRepository;
import finalproject.jpnshop.biz.repository.OrderRepository;
import finalproject.jpnshop.biz.repository.ProductRepository;
import finalproject.jpnshop.biz.repository.ReviewRepository;
import finalproject.jpnshop.util.SecurityUtil;
import finalproject.jpnshop.web.dto.ReqReview;
import finalproject.jpnshop.web.dto.ResReview;
import finalproject.jpnshop.web.dto.ResReview.Response;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final FileHandler fileHandler;
    private final ImageRepository imageRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;


    public List<ResReview.Response> getReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return getResponses(reviews);
    }

    public List<ResReview.Response> getReviewByProduct(Long productId) {
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

    @Transactional
    public void insertReview(ReqReview reviewForm, Long productId, List<MultipartFile> images)
        throws Exception {
        long memberId = SecurityUtil.getCurrentMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        reviewForm.setMember(member);
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        reviewForm.setProduct(product);

        OrderItem orderItem = orderItemRepository.findByProduct(product);

        Order order = orderRepository.findByMemberAndOrderItems(member, orderItem);

        if (!order.getMember().equals(member)) {
            throw new CustomException(ErrorCode.ORDER_NOT_FOUND);
        }

        Product newReviewProduct = reviewForm.getProduct();

        try {
            Review existReview = reviewRepository.findByMemberAndProduct(member, newReviewProduct);
            throw new CustomException(ErrorCode.REVIEW_ALREADY_EXIST);
        } catch (Exception e) {
            Review review = reviewForm.toEntity();
            List<Image> imageList = fileHandler.parseFileInfo(images);

            if (!imageList.isEmpty()) {
                for (Image image : imageList) {
                    review.addImage(imageRepository.save(image));
                }
            }
            reviewRepository.save(review);
        }

    }

    @Transactional
    public void updateReview(ReqReview reviewForm, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
            () -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));
        review.setTitle(reviewForm.getTitle());
        review.setContent(reviewForm.getContent());
        review.setStar(reviewForm.getStar());
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
