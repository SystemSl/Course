package com.example.course.Entities;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Restaurant {

    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private KitchenType kitchenType;

    @NotBlank
    @DecimalMin("0.0")
    private BigDecimal averageBill;

    @NotBlank
    private BigDecimal rating;

    public enum KitchenType {
        ITALIAN, CHINESE, JAPANESE, INDIAN, FRENCH, OTHER
    }
}