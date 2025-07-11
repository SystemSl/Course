package com.example.course;

import com.example.course.Entities.Restaurant;
import com.example.course.Entities.Review;
import com.example.course.Entities.Visitor;
import com.example.course.Services.RestaurantService;
import com.example.course.Services.ReviewService;
import com.example.course.Services.VisitorService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("test")
public class DataInitializer {

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ReviewService reviewService;

    @PostConstruct
    public void initializeData() {
        Visitor visitor1 = new Visitor();
        visitor1.setId(1L);
        visitor1.setName("Иван Иванов");
        visitor1.setAge(30);
        visitor1.setGender("Male");
        visitorService.save(visitor1);

        Visitor visitor2 = new Visitor();
        visitor1.setId(2L);
        visitor2.setAge(25);
        visitor2.setGender("Male");
        visitorService.save(visitor2);

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setId(1L);
        restaurant1.setName("Пицца");
        restaurant1.setKitchenType(Restaurant.KitchenType.ITALIAN);
        restaurant1.setAverageBill(new BigDecimal("1500"));
        restaurantService.save(restaurant1);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setId(2L);
        restaurant2.setName("Роллы");
        restaurant2.setKitchenType(Restaurant.KitchenType.JAPANESE);
        restaurant2.setAverageBill(new BigDecimal("2000"));
        restaurantService.save(restaurant2);

        Review review1 = new Review();
        review1.setId(1L);
        review1.setVisitorId(visitor1.getId());
        review1.setRestaurantId(restaurant1.getId());
        review1.setRating(4);
        review1.setComment("Понравилось!");
        reviewService.save(review1);

        Review review2 = new Review();
        review1.setId(2L);
        review2.setVisitorId(visitor2.getId());
        review2.setRestaurantId(restaurant2.getId());
        review2.setRating(5);
        review2.setComment("Хорошие роллы!");
        reviewService.save(review2);
    }
}
