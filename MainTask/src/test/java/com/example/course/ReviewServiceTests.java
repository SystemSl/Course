package com.example.course;

import com.example.course.Config.ReviewPK;
import com.example.course.Dto.Requests.ReviewRequestDTO;
import com.example.course.Dto.Responses.ReviewResponseDTO;
import com.example.course.Entities.Review;
import com.example.course.Mappers.ReviewMapper;
import com.example.course.Repositories.RestaurantRepository;
import com.example.course.Repositories.ReviewRepository;
import com.example.course.Repositories.VisitorRepository;
import com.example.course.Services.RestaurantService;
import com.example.course.Services.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTests {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private VisitorRepository visitorRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    public void shouldSaveReviewSuccessfully() {
        ReviewRequestDTO dto = new ReviewRequestDTO(1L, 1L, 5, "Great place!");
        Review review = new Review();
        given(reviewMapper.toEntity(any())).willReturn(review);
        given(reviewRepository.save(any())).willReturn(review);
        given(reviewMapper.toResponseDTO(any())).willReturn(new ReviewResponseDTO(1L, 1L, 5, "Great place!"));

        ReviewResponseDTO result = reviewService.save(dto);

        assertThat(result).isNotNull();
    }

    @Test
    public void shouldFindAllReviews() {
        Review review1 = new Review();
        Review review2 = new Review();
        given(reviewRepository.findAll()).willReturn(Arrays.asList(review1, review2));
        given(reviewMapper.toResponseDTO(any())).willReturn(new ReviewResponseDTO(1L, 1L, 5, "Great place!"));

        List<ReviewResponseDTO> result = reviewService.findAll();

        assertThat(result).hasSize(2);
    }

    @Test
    public void shouldRemoveReview() {
        ReviewPK id = new ReviewPK();
        id.setVisitorId(1L);
        id.setRestaurantId(1L);
        given(reviewRepository.findById(id)).willReturn(Optional.of(new Review()));

        reviewService.remove(id);

        verify(reviewRepository).deleteById(id);
    }

    @Test
    public void shouldUpdateReview() {
        ReviewPK id = new ReviewPK();
        id.setVisitorId(1L);
        id.setRestaurantId(1L);

        Review review = new Review();
        review.setId(id);
        review.setRating(5);
        review.setComment("Great place!");

        given(reviewRepository.findById(id)).willReturn(Optional.of(review));
        given(reviewMapper.toEntity(any())).willReturn(review);
        given(reviewRepository.save(any())).willReturn(review);
        given(reviewMapper.toResponseDTO(any())).willReturn(new ReviewResponseDTO(1L, 1L, 4, "Still good but can improve!"));

        ReviewRequestDTO dto = new ReviewRequestDTO(1L, 1L, 4, "Still good but can improve!");

        ReviewResponseDTO result = reviewService.update(dto);

        assertThat(result).isNotNull();
    }
}