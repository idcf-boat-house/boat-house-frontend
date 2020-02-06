package com.idcf.boathouse.Controller;

import com.idcf.boathouse.JdbcUtils;
import com.idcf.boathouse.Models.FoodCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/BoatHouse")
public class BoatHouseController {

	@RequestMapping("/AddFoodCategory")
    public void AddFoodCategory(@RequestParam("name") String name){
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
		String sql = "insert into FoodCategory (name) values (?)";
		List<Object> params = new ArrayList<Object>();
		params.add(name);
		try {
			boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
			System.out.println(flag);
			jdbcUtils.releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return;
    }

	@RequestMapping("/DeleteFoodCategory")
	public void DeleteFoodCategory(@RequestParam("name") String name){
		JdbcUtils jdbcUtils = new JdbcUtils();
		jdbcUtils.getConnection();
		String sql = "delete from FoodCategory where name = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(name);
		try {
			boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
			System.out.println(flag);
			jdbcUtils.releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	@RequestMapping("/UpdateFoodCategory")
	public void UpdateFoodCategory(@RequestParam("id") int id, @RequestParam("name") String name){
		JdbcUtils jdbcUtils = new JdbcUtils();
		jdbcUtils.getConnection();
		String sql = "update FoodCategory set name = ? where id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(name);
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

	@RequestMapping("/GetFoodCategories")
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

	@RequestMapping("/GetFoodCategory")
	public FoodCategory GetFoodCategory(@RequestParam("id") int id){
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
