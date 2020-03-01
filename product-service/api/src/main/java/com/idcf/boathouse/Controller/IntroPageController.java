package com.idcf.boathouse.controller;

import com.idcf.boathouse.dbentity.IntroPageEntity;
import com.idcf.boathouse.JdbcUtils;
import com.idcf.boathouse.models.IntroPage;
import com.idcf.boathouse.models.IntroPageValues;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "Boat House Introduce API")
@RequestMapping("/Intro/*")
public class IntroPageController {

	@RequestMapping(value = "IntroPage", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("添加介绍页内容")
	public void InserIntroPage(@RequestBody IntroPage introPage) throws SQLException{
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();

		String sqlSelect = "select * from intropage where page_id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(introPage.page_id);
		try {
			List<Map<String, Object>> list = jdbcUtils.findModeResult(sqlSelect, params);
			//需要更新
			if(list!=null&&list.size()>0){
				String updateSql = "update intropage set  page_title = ? ,page_api_url=?,text=?,image=?,deleted=0 ,create_time=NOW(),update_time=NOW() WHERE page_id = ?";
				List<Object> updateParams = new ArrayList<Object>();
				updateParams.add(introPage.page_title);
				updateParams.add(introPage.page_api_url);
				if(introPage.page_values!=null){
					updateParams.add(introPage.page_values.text);
					updateParams.add(introPage.page_values.image);
				}
				else {
					updateParams.add("");
					updateParams.add("");
				}
				updateParams.add(introPage.page_id);
				boolean flag = jdbcUtils.updateByPreparedStatement(updateSql, updateParams);
			}
			//需要插入
			else{
				String insertSql = "insert into intropage ( page_id,page_title,page_api_url,text,image,deleted,create_time,update_time) values ( ?,?,?,?,?,0,NOW(),NOW())";
				List<Object> insertParams = new ArrayList<Object>();
				insertParams.add(introPage.page_id);
				insertParams.add(introPage.page_title);
				insertParams.add(introPage.page_api_url);
				if(introPage.page_values!=null){
					insertParams.add(introPage.page_values.text);
					insertParams.add(introPage.page_values.image);
				}
				else {
					insertParams.add("");
					insertParams.add("");
				}
				boolean flag = jdbcUtils.updateByPreparedStatement(insertSql, insertParams);
			}
			jdbcUtils.releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
			jdbcUtils.releaseConn();
		}
    }

	@RequestMapping(value = "IntroPage", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("删除介绍页内容")
	public void DeleteFoodCategory(@RequestParam String page_id ){
		JdbcUtils jdbcUtils = new JdbcUtils();
		jdbcUtils.getConnection();
		String sql = "update  intropage set deleted=1 where page_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(page_id);
		try {
			boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
			System.out.println(flag);
			jdbcUtils.releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	@RequestMapping(value = "IntroPage", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("更新介绍页内容")
	public void UpdateIntroPage(@RequestBody IntroPage introPage){
		JdbcUtils jdbcUtils = new JdbcUtils();
		jdbcUtils.getConnection();
		String updateSql = "update intropage set  page_title = ? ,page_api_url=?,text=?,image=?,deleted=0 ,update_time=NOW() WHERE page_id = ?";
		List<Object> updateParams = new ArrayList<Object>();
		updateParams.add(introPage.page_title);
		updateParams.add(introPage.page_api_url);
		if(introPage.page_values!=null){
			updateParams.add(introPage.page_values.text);
			updateParams.add(introPage.page_values.image);
		}
		else {
			updateParams.add("");
			updateParams.add("");
		}
		updateParams.add(introPage.page_id);

		try {
			boolean flag = jdbcUtils.updateByPreparedStatement(updateSql, updateParams);
			jdbcUtils.releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}


	@RequestMapping(value = "IntroPage", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("根据Id获取介绍页")
	public IntroPage GetFoodCategory(@RequestParam String page_id){
		JdbcUtils jdbcUtils = new JdbcUtils();
		jdbcUtils.getConnection();
		String sql = "select * from intropage where page_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(page_id);
		IntroPage introPage=new IntroPage();
		try {
			IntroPageEntity introPageEntity= jdbcUtils.findSimpleRefResult(sql, params, IntroPageEntity.class);
			if(introPageEntity!=null){
				introPage.page_id=introPageEntity.page_id;
				introPage.page_title=introPageEntity.page_title;
				introPage.page_api_url=introPageEntity.page_api_url;
				introPage.page_values=new IntroPageValues();
				introPage.page_values.text=introPageEntity.text;
				introPage.page_values.image=introPageEntity.image;
			}
			jdbcUtils.releaseConn();
			return introPage;
		} catch (Exception e) {
		    e.printStackTrace();
			return null;
		}
	}
}
