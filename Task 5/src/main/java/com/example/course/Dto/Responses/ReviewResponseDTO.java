package com.example.course.Dto.Responses;

public record ReviewResponseDTO(Long id, Long visitorId, Long restaurantId, int rating, String comment) {}
