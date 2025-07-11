package com.example.course.Services;

import com.example.course.Dto.Requests.RestaurantRequestDTO;
import com.example.course.Dto.Responses.RestaurantResponseDTO;
import com.example.course.Entities.Restaurant;
import com.example.course.Entities.Review;
import com.example.course.Mappers.RestaurantMapper;
import com.example.course.Repositories.RestaurantRepository;
import com.example.course.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantMapper restaurantMapper;

    public RestaurantResponseDTO save(RestaurantRequestDTO dto) {
        Restaurant restaurant = restaurantMapper.toEntity(dto);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toResponseDTO(savedRestaurant);
    }

    public List<RestaurantResponseDTO> findAll() {
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void remove(Long id) {
        restaurantRepository.deleteById(id);
    }

    public RestaurantResponseDTO update(Long id, RestaurantRequestDTO dto) {
        Restaurant existingRestaurant = restaurantRepository.findById(id);
        Restaurant updatedRestaurant = restaurantMapper.toEntity(dto);
        updatedRestaurant.setId(existingRestaurant.getId());
        Restaurant savedRestaurant = restaurantRepository.save(updatedRestaurant);
        return restaurantMapper.toResponseDTO(savedRestaurant);
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

            Restaurant restaurant = restaurantRepository.findById(restaurantId);

            if (restaurant != null) {
                restaurant.setRating(avgRating);
            }
        }
    }
}
