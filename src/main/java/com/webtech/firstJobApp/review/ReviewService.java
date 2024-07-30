package com.webtech.firstJobApp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    Review getReviewById(Long companyId, Long reviewId);
    boolean createReview(Long companyId, Review review);
    boolean updateReview(Review review, Long id);
    boolean deleteReview(Long id);

}
