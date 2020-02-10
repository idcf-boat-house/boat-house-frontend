package com.idcf.boathouse;

import com.idcf.boathouse.Controller.BoatHouseController;
import com.idcf.boathouse.Models.FoodCategoryPost;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoathouseApplicationTests {

    @Mock
    private BoatHouseController boatHouseController;  // 被测类

    public BoathouseApplicationTests() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    // 在@Test标注的测试方法之前运行
    @Before
    public void setUp() throws Exception {
        // 初始化测试用例类中由Mockito的注解标注的所有模拟对象
        MockitoAnnotations.initMocks(this);
        // 用模拟对象创建被测类对象
        boatHouseController = new BoatHouseController();
    }

    @After
    public void tearDown() {
    }


    @Test
    public void AddFoodCategory() {
        boatHouseController.AddFoodCategory(new FoodCategoryPost());
    }

    @Test
    public void DeleteFoodCategory() {
        boatHouseController.DeleteFoodCategory(new FoodCategoryPost());
    }

    @Test
    public void UpdateFoodCategory() {
        boatHouseController.UpdateFoodCategory(new FoodCategoryPost());
    }

    @Test
    public void GetFoodCategories() {
        boatHouseController.GetFoodCategories();
    }

    @Test
    void contextLoads() {

    }

}
