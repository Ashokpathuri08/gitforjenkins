package com.foodhub.foodservice.controllertest;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodhub.foodservice.dto.FoodMenuDTO;
import com.foodhub.foodservice.entity.FoodMenu;
import com.foodhub.foodservice.service.FoodMenuService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
public class FoodMenuControllerTest {

    @MockBean
    FoodMenuService foodMenuService;
    @Autowired
    MockMvc mockMvc;
    @Mock
    private ModelMapper modelMapper;
    @MockBean
    private FoodMenu foodMenu;

    @Test
    public void test_crateFood() throws Exception {
        FoodMenuDTO food = new FoodMenuDTO(1L, "pizza","AchhiMasala",
                250.00,true,"veg",4.5,1200L);
        when(foodMenuService.createFood(food)).thenReturn(foodMenu);
        mockMvc.perform(post("/foodhub/fsapi/food-menu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(food)))
                .andExpect(status().isCreated());

    }
    @Test
    public void test_getFoodById() throws Exception {
        FoodMenuDTO food = new FoodMenuDTO(1L, "pizza","AchhiMasala",
                250.00,true,"veg",4.5,1200L);
        when(foodMenuService.getFoodById(1l)).thenReturn(food);
        mockMvc.perform(get("/foodhub/fsapi/food-menu/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(food)))
                .andExpect(status().isOk());

    }
    @Test
    public void test_getFoodByList() throws Exception {
        List<FoodMenu> food = Arrays.asList(new FoodMenu(1L, "veg","pizza",
                "Use Achimasala",250.00,true,4.5,1200L));
        when(foodMenuService.getAllFood()).thenReturn((List<FoodMenu>) food);
        mockMvc.perform(get("/foodhub/fsapi/food-menu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(food)))
                .andExpect(status().isAccepted());

    }
    @Test
    public void test_deleteFoodById() throws Exception {
         FoodMenu food = new FoodMenu (1L, "pizza","AchhiMasala", "veg",
                 250.00,true,4.5,1200L);
        when(foodMenuService.deleteFood(1L)).thenReturn(food.isAvailable());
        mockMvc.perform(delete("/foodhub/fsapi/food-menu/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(food)))
                .andExpect(status().isOk());

    }
    @Test
    public void test_updateFoodById() throws Exception {
        FoodMenuDTO food = new FoodMenuDTO(1L, "pizza","AchhiMasala",
                250.00,true,"veg",4.5,1200L);
        when(foodMenuService.updateFood(1L,food)).thenReturn(foodMenu);
        mockMvc.perform(put("/foodhub/fsapi/food-menu/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(food)))
                .andExpect(status().isOk());

    }
    @Test
    public void test_getFoodByCategory() throws Exception {
        List<FoodMenu> food = Arrays.asList(new FoodMenu(1L, "veg","pizza",
                "Use Achimasala",250.00,true,4.5,1200L));
        when(foodMenuService.findByCategory("veg")).thenReturn((List<FoodMenu>) food);
          mockMvc.perform(get("/foodhub/fsapi/food-menu/category/veg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(food)))
                .andExpect(status().isOk());

    }
    @Test
    public void test_getFoodBySearch() throws Exception {
        List<FoodMenu> food = Arrays.asList(new FoodMenu(1L, "veg","pizza",
                "Use Achimasala",250.00,true,4.5,1200L));
        when(foodMenuService.searchFood("pi")).thenReturn(food);
        mockMvc.perform(get("/foodhub/fsapi/food-menu/search?query=pi"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(food)));
    }


}

