package com.foodhub.foodservice.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food_menu")
public class FoodMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Invalid First Name: Empty first name")
    @NotNull(message = "Invalid First Name: First name is NULL")
    @Size(min = 3, max = 15, message = "Invalid First Name: Exceeds 15 characters")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;


    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "review_count")
    private Long reviewCount;
}

