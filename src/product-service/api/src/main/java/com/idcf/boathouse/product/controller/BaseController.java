package com.idcf.boathouse.product.controller;

import org.springframework.web.bind.annotation.*;

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
