package com.foodhub.foodservice.service;

import com.foodhub.foodservice.repository.FoodMenuRepo;
//import com.Foodservice.Exceptions.FoodNotFoundException;
import com.foodhub.foodservice.config.ItemNotFoundException;
import com.foodhub.foodservice.dto.FoodMenuDTO;
import com.foodhub.foodservice.entity.FoodMenu;
import com.foodhub.foodservice.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FoodMenuService {
    @Autowired
    private FoodMenuRepo foodRepository;

    @Autowired
    private Utility utility;

    public List<FoodMenu> getAllFood() {
        return foodRepository.findAll();
    }

    public FoodMenuDTO getFoodById(Long id) throws ItemNotFoundException {

        FoodMenu foodMenu = foodRepository.findById(id)
                .orElseThrow(() -> throwException(String.valueOf(id)));
        return  utility.convertToDto(foodMenu);

    }

    private ItemNotFoundException throwException(String value) {

        throw new ItemNotFoundException("Item Not Found with ID: " + value);
    }
    public FoodMenu createFood(FoodMenuDTO food) {
        return foodRepository.save(utility.convertToEntity(food));
    }

    public FoodMenu updateFood(Long id, FoodMenuDTO food) throws ItemNotFoundException{

        FoodMenu object = foodRepository.findById(id)
                .orElseThrow(() -> throwException(String.valueOf(id)));
        return foodRepository.save(utility.convertToEntity(food));

//        return foodRepository.findById(id)
//                .map(f -> {
//                    f.setName(food.getName());
//                    f.setDescription(food.getDescription());
//                    f.setPrice(food.getPrice());
//                    f.setIngredients(food.getIngredients());
//                    f.setImageUrl(food.getImageUrl());
//                    f.setAvailable(food.isAvailable());
//                    f.setCategory(food.getCategory());
//                    return foodRepository.save(utility.convertToEntity());
//                })
//                .orElseThrow(() -> new ItemNotFoundException( "Item Not Found For Update"));
    }

    public boolean deleteFood(Long id) {

        Optional<FoodMenu> customer = foodRepository.findById(id);
        if (customer.isPresent()) {
            foodRepository.deleteById(id);
            return true;
        } else {
            throwException(String.valueOf(id));
            return false;
        }
    }


    public List<FoodMenu> findByCategory(String category) {

        return foodRepository.findByCategory(category);
    }




    //    public List<FoodMenu> searchFood(String query) throws  ItemNotFoundException{
//        return foodRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query,query);
//    }
    public List<FoodMenu> searchFood(String query) throws ItemNotFoundException {

        List<FoodMenu> food = foodRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query,query);
        if (food.isEmpty()) {
            throw new ItemNotFoundException("No food found with the given query: " + query);
        }
        return food;
    }

    public FoodMenu checkFoodItemAvailability(String itemName) {
        FoodMenu foodMenu = null;
        try{
            System.out.println("Item Name:"+itemName);
            foodMenu = foodRepository.findByNameContainingIgnoreCase(itemName);
            System.out.println("Result:"+foodMenu);
            return foodMenu;
        }
        catch (Exception ex){
            return new FoodMenu();
        }
    }
}
