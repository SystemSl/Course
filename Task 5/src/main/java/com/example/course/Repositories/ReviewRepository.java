package com.example.course.Repositories;

import com.example.course.Entities.Review;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepository {

    private final List<Review> reviews = new ArrayList<>();

    public void save(Review review) {
        reviews.add(review);
    }

    public void remove(Review review) {
        reviews.remove(review);
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
}