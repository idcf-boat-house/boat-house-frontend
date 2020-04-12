package com.idcf.boathouse.account.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idcf.boathouse.account.config.JwtUtil;
import com.idcf.boathouse.account.core.ResponseData;
import com.idcf.boathouse.account.entity.User;
import com.idcf.boathouse.account.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "登录注册接口")
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@ApiParam(value = "用户名", required = true) @RequestParam(value = "username", required = true) String username,
                        @ApiParam(value = "用户密码", required = true) @RequestParam(value = "password", required = true) String password) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.getOne(new QueryWrapper<User>().eq("account", username));
        if (user == null) {
            return new ResponseData(false, 500, "登录失败-用户名不存在", "用户名不存在");
        }
        String token = "";
        if (user.getPassword().equals(DigestUtil.md5Hex(password))) {
            token = JwtUtil.generateToken(user.getId().toString());
        } else {
            return new ResponseData(false, 500, "登录失败-用户名或密码错误", "用户名或密码错误");
        }
        result.put("token", token);
        result.put("userId", user.getId());
        result.put("username", username);
        if (!token.equals("")) {
            return ResponseData.success(result);
        } else {
            return new ResponseData(false, 500, "登录失败-生成用户密钥出错", "生成用户密钥出错！");
        }
    }

    /**
     * 该方法是注册用户的方法，默认放开访问控制
     */
    @ApiOperation("注册")
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    @ResponseBody
    public Object signUp(@ApiParam(value = "用户名", required = true) @RequestParam(value = "username", required = true) String username,
                         @ApiParam(value = "用户密码", required = true) @RequestParam(value = "password", required = true) String password) {
        User userExists = userService.getOne(new QueryWrapper<User>().eq("account", username));
        if (userExists != null) {
            return new ResponseData(true, 501, "请求失败-用户已存在，请直接登录", "用户已存在，请直接登录");
        }
        User user = new User();
        user.setAccount(username);
        user.setPassword(DigestUtil.md5Hex(password));
        Map<String, Object> result = new HashMap<>();
        result.put("result", userService.save(user));
        return new ResponseData(true, 200, "请求成功", result);
    }
}
