package com.foodhub.foodservice.repository;

import com.foodhub.foodservice.entity.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodMenuRepo extends JpaRepository<FoodMenu,Long> {

    List<FoodMenu> findByCategory(String category);

    List<FoodMenu> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String query, String query1);

    public FoodMenu findByNameContainingIgnoreCase(String itemName);
}
