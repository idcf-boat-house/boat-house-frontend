package com.idcf.boathouse.junit;

import com.idcf.boathouse.controller.BoatHouseController;
import com.idcf.boathouse.controller.FoodController;
import com.idcf.boathouse.models.FoodCategoryPost;
import com.idcf.boathouse.models.FoodPost;
import com.idcf.boathouse.services.FoodService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class FoodApplicationTests {
    @Mock
    private FoodController foodController;
    private FoodService foodService=new FoodService();

    @Test
    public void AddFood() {
        FoodPost foodPost = new FoodPost();
        foodPost.id = 1;
        foodPost.categoryId = 1;
        foodPost.name = "三明治";
        foodPost.price = BigDecimal.valueOf(1.00);
        foodPost.description = "最受欢迎的甜品";
        //foodController.addFood(foodPost);
    }
}
