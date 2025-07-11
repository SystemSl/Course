package com.example.course;

import com.example.course.Services.RestaurantService;
import com.example.course.Services.ReviewService;
import com.example.course.Services.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ReviewService reviewService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Visitors:");
        visitorService.findAll().forEach(System.out::println);

        System.out.println("Restaurants:");
        restaurantService.findAll().forEach(System.out::println);

        System.out.println("Reviews:");
        reviewService.findAll().forEach(System.out::println);
    }
}
