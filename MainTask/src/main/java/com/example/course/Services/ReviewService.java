package com.example.course.Services;

import com.example.course.Dto.Requests.ReviewRequestDTO;
import com.example.course.Dto.Responses.ReviewResponseDTO;
import com.example.course.Entities.Review;
import com.example.course.Mappers.ReviewMapper;
import com.example.course.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private RestaurantService restaurantService;

    public ReviewResponseDTO save(ReviewRequestDTO dto) {
        Review review = reviewMapper.toEntity(dto);
        Review savedReview = reviewRepository.save(review);
        restaurantService.recalculateRatingAfterNewReview(review.getRestaurantId());
        return reviewMapper.toResponseDTO(savedReview);
    }

    public List<ReviewResponseDTO> findAll() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void remove(Long id) {
        reviewRepository.deleteById(id);
    }

    public ReviewResponseDTO update(Long id, ReviewRequestDTO dto) {
        Review existingReview = reviewRepository.findById(id);
        Review updatedReview = reviewMapper.toEntity(dto);
        updatedReview.setId(existingReview.getId());
        Review savedReview = reviewRepository.save(updatedReview);
        return reviewMapper.toResponseDTO(savedReview);
    }
}
