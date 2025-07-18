package com.example.course.Dto.Responses;

public record ReviewResponseDTO(Long visitorId, Long restaurantId, int rating, String comment) {
}
