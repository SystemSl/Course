package com.example.course.Repositories;

import com.example.course.Entities.Restaurant;
import com.example.course.Entities.Visitor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepository {

    private Long idCounter = 1L;

    private final List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant save(Restaurant restaurant) {
        restaurant.setId(idCounter);
        idCounter++;
        restaurants.add(restaurant);
        return restaurant;
    }

    public List<Restaurant> findAll() {
        return restaurants;
    }

    public void deleteById(Long id) {
        restaurants.removeIf(restaurant -> restaurant.getId().equals(id));
    }

    public Restaurant findById(Long id) {
        return restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}