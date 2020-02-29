package com.idcf.boathouse.controller;

import com.idcf.boathouse.models.Food;
import com.idcf.boathouse.models.FoodPost;
import com.idcf.boathouse.services.FoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BaseController {
	public static final Integer CODE_OK = 0;//正常
	public static final Integer CODE_ERROR = 1;//错误
	public Map<String, Object> info(Integer errcode, String message, Object obj){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map map = new HashMap();
		map.put("errcode", errcode);
		map.put("message", message);
		map.put("data", obj);
        return map;
    }
}
