package com.example.course.Entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Visitor {

    @NotBlank
    private Long id;

    private String name;

    @PositiveOrZero
    private int age;

    @NotBlank
    private String gender;
}