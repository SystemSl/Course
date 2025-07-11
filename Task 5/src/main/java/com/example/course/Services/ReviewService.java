package com.example.course.Services;

import com.example.course.Entities.Review;
import com.example.course.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantService restaurantService;

    public void save(Review review) {
        reviewRepository.save(review);
        restaurantService.recalculateRatingAfterNewReview(review.getRestaurantId());
    }

    public void remove(Review review) {
        reviewRepository.remove(review);
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
}
