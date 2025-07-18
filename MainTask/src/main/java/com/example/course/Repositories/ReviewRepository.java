package com.example.course.Repositories;

import com.example.course.Config.ReviewPK;
import com.example.course.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewPK> {
}
