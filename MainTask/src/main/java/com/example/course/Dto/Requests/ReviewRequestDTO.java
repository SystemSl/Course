package com.example.course.Dto.Requests;

public record ReviewRequestDTO(Long visitorId, Long restaurantId, int rating, String comment) {}
