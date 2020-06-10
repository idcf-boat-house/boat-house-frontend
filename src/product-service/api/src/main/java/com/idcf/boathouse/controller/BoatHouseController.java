package com.idcf.boathouse.controller;

import com.idcf.boathouse.JdbcUtils;
import com.idcf.boathouse.models.FoodCategory;
import com.idcf.boathouse.models.FoodCategoryPost;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "Boat House Test API")
@RequestMapping("/BoatHouse/*")
public class BoatHouseController {

	@RequestMapping(value = "FoodCategory", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("添加菜品分类")
	public void AddFoodCategory(@RequestBody FoodCategoryPost foodCategoryPost){
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
		String sql = "insert into FoodCategory (name,description) values (?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(foodCategoryPost.name);
		params.add(foodCategoryPost.description);
		try {
			boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
			System.out.println(flag);
			jdbcUtils.releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return;
    }

	@RequestMapping(value = "FoodCategory", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("删除菜品分类")
	public void DeleteFoodCategory(@RequestParam String id){
		JdbcUtils jdbcUtils = new JdbcUtils();
		jdbcUtils.getConnection();
		String sql = "delete from FoodCategory where Id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		try {
			boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
			System.out.println(flag);
			jdbcUtils.releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	@RequestMapping(value = "FoodCategory", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("更新菜品分类")
	public void UpdateFoodCategory(@RequestBody FoodCategoryPost foodCategoryPost){
		JdbcUtils jdbcUtils = new JdbcUtils();
		jdbcUtils.getConnection();
		String sql = "update FoodCategory set name = ?, description = ? where id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(foodCategoryPost.name);
		params.add(foodCategoryPost.description);
		params.add(foodCategoryPost.id);
		try {
			boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
			System.out.println(flag);
			jdbcUtils.releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	@RequestMapping(value = "FoodCategories", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("获取所有菜品分类")
	public List<Map<String, Object>> GetFoodCategories(){
		JdbcUtils jdbcUtils = new JdbcUtils();
		jdbcUtils.getConnection();
		String sql = "select * from FoodCategory";
		try {
			List<Map<String, Object>> list = jdbcUtils.findModeResult(sql, null);
			System.out.println(list);
			jdbcUtils.releaseConn();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "FoodCategory", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("根据Id获取菜品分类")
	public FoodCategory GetFoodCategory(@RequestParam String id){
		JdbcUtils jdbcUtils = new JdbcUtils();
		jdbcUtils.getConnection();
		String sql = "select * from FoodCategory where Id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		FoodCategory foodCategory;
		try {
			foodCategory = jdbcUtils.findSimpleRefResult(sql, params, FoodCategory.class);
		    System.out.print(foodCategory);
			jdbcUtils.releaseConn();
			return foodCategory;
		} catch (Exception e) {
		    e.printStackTrace();
			return null;
		}
	}
}
