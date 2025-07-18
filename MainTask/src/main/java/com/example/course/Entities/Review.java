package com.example.course.Entities;

import com.example.course.Config.ReviewPK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "review_table")
public class Review {

    @EmbeddedId
    private ReviewPK id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("visitorId")
    @JoinColumn(name = "visitor_id", nullable = false)
    private Visitor visitor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("restaurantId")
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comment")
    private String comment;
}