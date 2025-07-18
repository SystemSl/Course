package com.example.course.Services;

import com.example.course.Config.ReviewPK;
import com.example.course.Dto.Requests.ReviewRequestDTO;
import com.example.course.Dto.Responses.ReviewResponseDTO;
import com.example.course.Entities.Restaurant;
import com.example.course.Entities.Review;
import com.example.course.Mappers.ReviewMapper;
import com.example.course.Repositories.RestaurantRepository;
import com.example.course.Repositories.ReviewRepository;
import com.example.course.Repositories.VisitorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private RestaurantService restaurantService;

    public ReviewResponseDTO save(ReviewRequestDTO dto) {
        Review review = reviewMapper.toEntity(dto);
        review.setVisitor(visitorRepository.findById(dto.visitorId()).orElse(null));
        review.setRestaurant(restaurantRepository.findById(dto.restaurantId()).orElse(null));
        reviewRepository.save(review);
        restaurantService.recalculateRatingAfterNewReview(review.getRestaurant());
        return reviewMapper.toResponseDTO(review);
    }

    public List<ReviewResponseDTO> findAll() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void remove(ReviewPK id) {
        Restaurant restaurant = reviewRepository.findById(id).orElse(null).getRestaurant();
        reviewRepository.deleteById(id);
        restaurantService.recalculateRatingAfterNewReview(restaurant);
    }

    public ReviewResponseDTO update(ReviewRequestDTO dto) {
        ReviewPK id = new ReviewPK();
        id.setRestaurantId(dto.restaurantId());
        id.setVisitorId(dto.visitorId());

        Review existingReview = reviewRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Review not found"));

        Review updatedReview = reviewMapper.toEntity(dto);
        updatedReview.setId(existingReview.getId());
        updatedReview.setVisitor(existingReview.getVisitor());
        updatedReview.setRestaurant(existingReview.getRestaurant());

        Review savedReview = reviewRepository.save(updatedReview);
        if (savedReview == null) {
            throw new PersistenceException("Failed to save review");
        }

        restaurantService.recalculateRatingAfterNewReview(savedReview.getRestaurant());
        return reviewMapper.toResponseDTO(savedReview);
    }
}
