package com.foodhub.foodservice.controller;

//import com.Foodservice.Exceptions.FoodNotFoundException;
import com.foodhub.foodservice.config.ItemNotFoundException;
import com.foodhub.foodservice.service.FoodMenuService;
import com.foodhub.foodservice.dto.FoodMenuDTO;
import com.foodhub.foodservice.entity.FoodMenu;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/foodhub/fsapi")
@Slf4j
public class FoodMenuController {
    @Autowired
    private FoodMenuService foodService;

    Logger logger = LoggerFactory.getLogger(FoodMenuController.class);



    @GetMapping("/food-menu")
    public ResponseEntity<List<FoodMenu>> getAllFood() {
        List<FoodMenu> foodMenu = foodService.getAllFood();
        return new ResponseEntity<>(foodMenu, HttpStatus.ACCEPTED);
    }

    @GetMapping("/food-menu/{id}")
    public ResponseEntity<FoodMenuDTO> getFoodById(@PathVariable Long id) throws ItemNotFoundException {
        FoodMenuDTO foodMenuDto = foodService.getFoodById(id);
        return new ResponseEntity<>(foodMenuDto, HttpStatus.OK);
    }

    @PostMapping("/food-menu")
    public ResponseEntity<FoodMenu> createFood(@RequestBody @Valid FoodMenuDTO food) {
        try {
            FoodMenu object = foodService.createFood(food);
            return new ResponseEntity<>(object, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/food-menu/{id}")
    public ResponseEntity<FoodMenu> updateFood(@PathVariable Long id, @RequestBody FoodMenuDTO food) throws  ItemNotFoundException{
        try {
            return new ResponseEntity<FoodMenu>(foodService.updateFood(id, food), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/food-menu/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable Long id) {
        boolean deleteFoodById = foodService.deleteFood(id);
        if (deleteFoodById) {
            return new ResponseEntity<>(("Customer deleted - Customer ID:" + id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(("Customer deletion failed - Customer ID:" + id), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/food-menu/category/{category}")
    public List<FoodMenu> getFoodByCategory(@PathVariable("category") String category) throws ItemNotFoundException {
        List<FoodMenu> food = foodService.findByCategory(category);
        if (food.isEmpty()) {
            throw new ItemNotFoundException("No food found with the given category: " + category);
        }
        return food;
    }

    //@GetMapping("/search")
//    public List<FoodMenu> searchForFood(@RequestParam("query") String query) throws  ItemNotFoundException{
//
//        return foodService.searchFood(query);
    //}
    @GetMapping("/food-menu/search")
    public List<FoodMenu> searchForFood(@RequestParam("query") String query){

        try {
            return foodService.searchFood(query);
        } catch (ItemNotFoundException e) {
            return (List<FoodMenu>) new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/food-menu/inquiry/{itemName}")
    public ResponseEntity<FoodMenu> inquireFoodItemAvailabilityByItemName(@PathVariable String itemName){
        System.out.println("Coming Here from order service.....");
        FoodMenu foodMenu = foodService.checkFoodItemAvailability(itemName);
        return new ResponseEntity<>(foodMenu, HttpStatus.OK);
    }


}

