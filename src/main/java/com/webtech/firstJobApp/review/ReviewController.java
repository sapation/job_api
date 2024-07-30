package com.webtech.firstJobApp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,  @PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(companyId, reviewId);
        return review != null ? new ResponseEntity<>(review, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean isReviewSaved = reviewService.createReview(companyId, review);
        return isReviewSaved ?
                new ResponseEntity<>("Review added Successfully", HttpStatus.CREATED) :
                new ResponseEntity<>("Review not Saved", HttpStatus.NOT_FOUND);
    }
}
