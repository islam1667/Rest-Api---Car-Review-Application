package com.company.api.services.impl;

import com.company.api.dto.ReviewDto;
import com.company.api.exceptions.CarNotFoundException;
import com.company.api.exceptions.ReviewNotFoundException;
import com.company.api.models.Car;
import com.company.api.models.Review;
import com.company.api.repositories.CarRepository;
import com.company.api.repositories.ReviewRepository;
import com.company.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CarRepository carRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, CarRepository carRepository) {
        this.reviewRepository = reviewRepository;
        this.carRepository = carRepository;
    }

    @Override
    public ReviewDto createReview(Integer carId, ReviewDto dto) {
        Review review = mapToEntity(dto);
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException("Car not found, review not added"));
        review.setCar(car);
        Review savedReview = reviewRepository.save(review);
        return mapToDto(savedReview);
    }

    @Override
    public List<ReviewDto> getReviewsByCarId(Integer carId) {
        List<Review> reviews = reviewRepository.findByCarId(carId);
        return reviews.stream().map(this::mapToDto).toList();
    }

    @Override
    public ReviewDto getReviewById(Integer reviewId, Integer carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException("Car with associated review not found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException(("Review with associated car not found")));

        if(!review.getCar().getId().equals(car.getId())) throw new ReviewNotFoundException("This review does not belong to car");

        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(Integer carId, Integer reviewId, ReviewDto reviewDto) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException("Car with associated review not found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException(("Review with associated car not found")));

        if(!review.getCar().getId().equals(car.getId())) throw new ReviewNotFoundException("This review does not belong to car");

        review.setTitle(reviewDto.getTitle());
        review.setStars(reviewDto.getStars());
        review.setContent(reviewDto.getContent());

        Review updatedReview = reviewRepository.save(review);

        return mapToDto(updatedReview);
    }

    @Override
    public void deleteReview(Integer carId, Integer reviewId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException("Car with associated review not found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException(("Review with associated car not found")));

        if(!review.getCar().getId().equals(car.getId())) throw new ReviewNotFoundException("This review does not belong to car");

        reviewRepository.delete(review);
    }

    private ReviewDto mapToDto(Review review){
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setContent(review.getContent());
        dto.setStars(review.getStars());
        dto.setTitle(review.getTitle());
        return dto;
    }

    private Review mapToEntity(ReviewDto dto){
        Review review = new Review();
        review.setId(dto.getId());
        review.setContent(dto.getContent());
        review.setStars(dto.getStars());
        review.setTitle(dto.getTitle());
        return review;
    }
}
