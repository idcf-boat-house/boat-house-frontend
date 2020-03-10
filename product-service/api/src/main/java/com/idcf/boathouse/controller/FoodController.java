package com.idcf.boathouse.controller;

import com.idcf.boathouse.JdbcUtils;
import com.idcf.boathouse.models.Food;
import com.idcf.boathouse.models.FoodCategory;
import com.idcf.boathouse.models.FoodCategoryPost;
import com.idcf.boathouse.models.FoodPost;
import com.idcf.boathouse.services.FoodService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "Boat House Food API")
@RequestMapping("/BoatHouse/*")
public class FoodController extends BaseController{

	@Autowired
	public FoodService foodService;

	@RequestMapping(value= "Food", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("添加菜品")
	public  Map<String, Object> addFood(@RequestParam(value = "菜品分类ID") Integer categoryId, @RequestParam(value = "菜品名称") String name,
			@RequestParam(value = "菜品价格") BigDecimal price, @RequestParam(value = "菜品描述") String description,
			@RequestParam(value = "菜品图片", required = false) String picture)  throws IllegalStateException, IOException {
		FoodPost foodPost = new FoodPost();
		foodPost.categoryId = categoryId;
		foodPost.name = name;
		foodPost.price = price;
		foodPost.description = description;
		foodPost.picture = picture;
		foodService.insertOrUpdateFood(foodPost);
		return super.info(BaseController.CODE_OK,"添加菜品成功", null);
	}

	@RequestMapping(value = "Food", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("更新菜品")
	public  Map<String, Object> addFood(@RequestParam(value = "菜品ID") Integer id, @RequestParam(value = "菜品分类ID") Integer categoryId,
										@RequestParam(value = "菜品名称") String name, @RequestParam(value = "菜品价格") BigDecimal price,
										@RequestParam(value = "菜品描述") String description,
										@RequestParam(value = "菜品图片", required = false) String picture)
			throws IllegalStateException, IOException {
		FoodPost foodPost = new FoodPost();
		foodPost.id = id;
		foodPost.categoryId = categoryId;
		foodPost.name = name;
		foodPost.price = price;
		foodPost.description = description;
		if(picture!=null)
		{
			foodPost.picture = picture;
		}
		foodService.insertOrUpdateFood(foodPost);
		return super.info(BaseController.CODE_OK,"更新菜品成功", null);
	}

	@RequestMapping(value = "Food", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("删除菜品")
	public Map<String, Object> DeleteFood(@RequestParam String id){
		foodService.deleteFood(id);
		return super.info(BaseController.CODE_OK,"删除菜品成功", null);
	}

	@RequestMapping(value = "Foods", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("获取所有菜品")
	public Map<String, Object> GetFoods(@RequestParam(required = false) Integer categoryId){
		List<Map<String, Object>> list = foodService.listFood(categoryId);
		return super.info(BaseController.CODE_OK, "获取菜品成功", list);
	}

	@RequestMapping(value = "Food", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("根据Id获取菜品")
	public Map<String, Object> GetFood(@RequestParam String id){
		Food food = foodService.getFood(id);
		return super.info(BaseController.CODE_OK, "获取菜品成功", food);
	}
}
