package com.webtech.firstJobApp.review.ReviewServiceImpl;

import com.webtech.firstJobApp.company.Company;
import com.webtech.firstJobApp.company.CompanyService;
import com.webtech.firstJobApp.review.Review;
import com.webtech.firstJobApp.review.ReviewRepository;
import com.webtech.firstJobApp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateReview(Review review, Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review reviewToUpdate = optionalReview.get();
            reviewToUpdate.setTitle(review.getTitle());
            reviewToUpdate.setDescription(review.getDescription());
            reviewToUpdate.setTitle(review.getTitle());
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
        }
        return false;
    }
}
