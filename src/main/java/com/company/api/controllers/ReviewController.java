package com.company.api.controllers;

import com.company.api.dto.ReviewDto;
import com.company.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/car/{carId}/review")
    public ResponseEntity<ReviewDto> createReview(@PathVariable("carId") Integer id, @RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(id, reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/car/{carId}/reviews")
    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable("carId") Integer id){
        return new ResponseEntity<>(reviewService.getReviewsByCarId(id), HttpStatus.OK);
    }

    @GetMapping("/car/{carId}/reviews/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable("carId") Integer carId, @PathVariable("id") Integer reviewId){
        ReviewDto dto = reviewService.getReviewById(reviewId, carId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/car/{carId}/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable("carId") Integer carId, @PathVariable("id") Integer reviewId,
                                                  @RequestBody ReviewDto reviewDto){
        ReviewDto updatedReview = reviewService.updateReview(carId, reviewId, reviewDto);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/car/{carId}/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("carId") Integer carId, @PathVariable("id") Integer reviewId){
        reviewService.deleteReview(carId, reviewId);
        return ResponseEntity.ok("Deleted successfully");
    }
}
