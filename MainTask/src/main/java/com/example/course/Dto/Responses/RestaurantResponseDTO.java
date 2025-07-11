package com.example.course.Dto.Responses;

import com.example.course.Entities.Restaurant;

import java.math.BigDecimal;

public record RestaurantResponseDTO(Long id, String name, String description, Restaurant.KitchenType kitchenType, BigDecimal averageBill, BigDecimal rating) {}
