package com.foodhub.foodservice.utility;

import com.foodhub.foodservice.dto.FoodMenuDTO;
import com.foodhub.foodservice.entity.FoodMenu;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Utility {

    private final ModelMapper modelMapper = new ModelMapper();

    public FoodMenuDTO convertToDto(FoodMenu foodMenu) {
        FoodMenuDTO foodMenuDTO = modelMapper.map(foodMenu, FoodMenuDTO.class);
        return foodMenuDTO;
    }

    public FoodMenu convertToEntity(FoodMenuDTO foodMenuDTO) {
        FoodMenu foodMenu = modelMapper.map(foodMenuDTO, FoodMenu.class);
        return foodMenu;
    }
}
