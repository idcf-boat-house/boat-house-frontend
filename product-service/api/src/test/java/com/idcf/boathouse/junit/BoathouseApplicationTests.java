package com.idcf.boathouse.junit;

import com.idcf.boathouse.controller.BoatHouseController;
import com.idcf.boathouse.models.FoodCategoryPost;
import com.idcf.boathouse.services.FoodCategoryService;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class BoathouseApplicationTests {

    private FoodCategoryService foodCategoryService=new FoodCategoryService();

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
        FoodCategoryPost foodCategoryPost = new FoodCategoryPost();
        foodCategoryPost.id = 1;
        foodCategoryPost.name = "三明治";
        boatHouseController.AddFoodCategory(foodCategoryPost);
    }

    @Test
    public void DeleteFoodCategory() {
        boatHouseController.DeleteFoodCategory("6");
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
    public void VerifyName() {
        assertEquals(true,foodCategoryService.VerifyName(("三明治")));
    }

    @Test
    void contextLoads() {

    }

}
