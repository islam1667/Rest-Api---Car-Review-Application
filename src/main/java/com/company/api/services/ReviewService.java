package com.company.api.services;

import com.company.api.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(Integer carId, ReviewDto review);

    List<ReviewDto> getReviewsByCarId(Integer carId);

    ReviewDto getReviewById(Integer reviewId, Integer carId);

    ReviewDto updateReview(Integer carId, Integer reviewId, ReviewDto reviewDto);

    void deleteReview(Integer carId, Integer reviewId);
}
