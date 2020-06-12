package com.idcf.boathouse.controller;

import com.idcf.boathouse.dbentity.IntroPageEntity;
import com.idcf.boathouse.JdbcUtils;
import com.idcf.boathouse.models.IntroPage;
import com.idcf.boathouse.models.IntroPageFront;
import com.idcf.boathouse.models.IntroPageValues;
import com.idcf.boathouse.services.IntroPageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
//@Api(tags = "Boat House Introduce API")
@Api(tags = "船坞故事接口")
//@RequestMapping("/Intro/*")
@RequestMapping("/intro/*")
public class IntroPageController {

	@Autowired
	private IntroPageService introPageService;
	@RequestMapping(value = "intro_page", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("添加介绍页内容")
	public void inserIntroPage(@RequestBody IntroPageFront introPage) throws SQLException{
		introPageService.inserIntroPage(introPage);
    }

	@RequestMapping(value = "intro_page/{page_id}", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("删除介绍页内容")
	public void deleteIntroPage(@PathVariable("page_id") String page_id ) throws Exception{
		introPageService.deleteIntroPage(page_id);
	}

	@RequestMapping(value = "intro_page", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("更新介绍页内容")
	public void updateIntroPage(@RequestBody IntroPageFront introPage) throws Exception{
		introPageService.updateIntroPage(introPage);
	}


	@RequestMapping(value = "intro_page/{page_id}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation("根据Id获取介绍页")
	//public IntroPage GetFoodCategory(@RequestParam String page_id){
	public IntroPageFront getIntroPage(@PathVariable("page_id") String page_id){
		return introPageService.getIntroPage(page_id);
	}
}
