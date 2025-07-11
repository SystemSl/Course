package com.example.course.Controllers;

import com.example.course.Dto.Requests.ReviewRequestDTO;
import com.example.course.Dto.Responses.ReviewResponseDTO;
import com.example.course.Services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> saveReview(@Validated @RequestBody ReviewRequestDTO dto) {
        ReviewResponseDTO createdReview = reviewService.save(dto);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> findAllReviews() {
        List<ReviewResponseDTO> reviews = reviewService.findAll();
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeReview(@PathVariable Long id) {
        reviewService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewRequestDTO dto) {
        ReviewResponseDTO updatedReview = reviewService.update(id, dto);
        return ResponseEntity.ok(updatedReview);
    }
}
