package com.example.course.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "restaurant_table")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "kitchen_type")
    @Enumerated(EnumType.STRING)
    private KitchenType kitchenType;

    @Column(name = "average_bill")
    private BigDecimal averageBill;

    @Column(name = "rating")
    private BigDecimal rating;

    public enum KitchenType {
        ITALIAN, CHINESE, JAPANESE, INDIAN, FRENCH, OTHER
    }
}