package com.example.course.Dto.Requests;

import com.example.course.Entities.Restaurant;

import java.math.BigDecimal;

public record RestaurantRequestDTO(String name, String description, Restaurant.KitchenType kitchenType, BigDecimal averageBill) {}
