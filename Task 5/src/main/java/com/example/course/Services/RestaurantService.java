package com.example.course.Services;

import com.example.course.Entities.Restaurant;
import com.example.course.Entities.Review;
import com.example.course.Repositories.RestaurantRepository;
import com.example.course.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public void save(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public void remove(Restaurant restaurant) {
        restaurantRepository.remove(restaurant);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
    public void recalculateRatingAfterNewReview(Long restaurantId) {
        List<Review> reviews = reviewRepository.findAll();
        List<Review> filteredReviews = reviews.stream()
                .filter(r -> r.getRestaurantId().equals(restaurantId))
                .toList();

        if (!filteredReviews.isEmpty()) {
            int sumRatings = filteredReviews.stream()
                    .mapToInt(Review::getRating)
                    .sum();
            BigDecimal avgRating = new BigDecimal(sumRatings).divide(BigDecimal.valueOf(filteredReviews.size()), 2, BigDecimal.ROUND_HALF_UP);

            Restaurant restaurant = restaurantRepository.findAll().stream()
                    .filter(r -> r.getId().equals(restaurantId))
                    .findFirst()
                    .orElse(null);

            if (restaurant != null) {
                restaurant.setRating(avgRating);
            }
        }
    }
}
