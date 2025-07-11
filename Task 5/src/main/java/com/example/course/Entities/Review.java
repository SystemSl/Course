package com.example.course.Entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Review {

    @NotBlank
    private Long id;

    @NotBlank
    private Long visitorId;

    @NotBlank
    private Long restaurantId;

    @Min(1)
    @Max(5)
    private int rating;

    private String comment;
}