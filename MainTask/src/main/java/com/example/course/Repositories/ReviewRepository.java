package com.example.course.Repositories;

import com.example.course.Entities.Review;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepository {

    private Long idCounter = 1L;

    private final List<Review> reviews = new ArrayList<>();

    public Review save(Review review) {
        review.setId(idCounter);
        idCounter++;
        reviews.add(review);
        return review;
    }

    public List<Review> findAll() {
        return reviews;
    }

    public Review findById(Long id) {
        return reviews.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteById(Long id) {
        reviews.removeIf(review -> review.getId().equals(id));
    }
}