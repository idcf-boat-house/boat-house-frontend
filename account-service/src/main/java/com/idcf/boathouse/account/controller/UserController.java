package com.idcf.boathouse.account.controller;

import com.idcf.boathouse.account.core.ResponseData;
import com.idcf.boathouse.account.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "用户管理接口")
@RequestMapping("/rest/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("提交加盟信息")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@ApiParam(value = "用户名", required = true) @RequestParam(value = "username", required = false) String username,
                     @ApiParam(value = "用户密码", required = true) @RequestParam(value = "password", required = false) String password) {
        Map<String,Object> result =  new HashMap<>();
        result.put("token","");
        result.put("userId",1);
        return new ResponseData(true,200,"请求成功",result);
    }
}
