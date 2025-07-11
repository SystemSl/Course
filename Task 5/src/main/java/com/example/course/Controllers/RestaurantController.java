package com.example.course.Controllers;

import com.example.course.Dto.Requests.RestaurantRequestDTO;
import com.example.course.Dto.Responses.RestaurantResponseDTO;
import com.example.course.Services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<RestaurantResponseDTO> saveRestaurant(@Validated @RequestBody RestaurantRequestDTO dto) {
        RestaurantResponseDTO createdRestaurant = restaurantService.save(dto);
        return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> findAllRestaurants() {
        List<RestaurantResponseDTO> restaurants = restaurantService.findAll();
        return ResponseEntity.ok(restaurants);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeRestaurant(@PathVariable Long id) {
        restaurantService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurant(@PathVariable Long id, @Valid @RequestBody RestaurantRequestDTO dto) {
        RestaurantResponseDTO updatedRestaurant = restaurantService.update(id, dto);
        return ResponseEntity.ok(updatedRestaurant);
    }
}
