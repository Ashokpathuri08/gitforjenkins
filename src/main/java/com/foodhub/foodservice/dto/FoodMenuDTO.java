package com.foodhub.foodservice.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.math.BigDecimal;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FoodMenuDTO {

    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Invalid   Name: Empty first name")
    @NotNull(message = "Invalid  Name:  name is NULL")
    @Size(min = 3, max = 15, message = "Invalid  Name: Exceeds 15 characters")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Invalid Price Name: Price  is NULL")
    private Double price;

    @Column(name = "is_available", nullable = false)
    @NotNull(message = "Invalid isAvailable: isAvailable  is NULL")
    private boolean isAvailable;

    @Column(name = "category")
    @NotNull(message = "Invalid Category Type: category  is NULL")
    private String category;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "review_count")
    private Long reviewCount;
}
