package com.example.course.Entities;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Restaurant {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public KitchenType getKitchenType() {
        return kitchenType;
    }

    public void setKitchenType(KitchenType kitchenType) {
        this.kitchenType = kitchenType;
    }

    public BigDecimal getAverageBill() {
        return averageBill;
    }

    public void setAverageBill(BigDecimal averageBill) {
        this.averageBill = averageBill;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }
}