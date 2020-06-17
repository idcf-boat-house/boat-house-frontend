package com.idcf.boathouse.account.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idcf.boathouse.account.core.ResponseData;
import com.idcf.boathouse.account.entity.User;
import com.idcf.boathouse.account.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "用户管理接口")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "增加用户")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseData createUser(@RequestBody User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        if (user.getPassword() == null || user.getPassword().equals("")) {
            user.setPassword(DigestUtil.md5Hex("111111"));
        }
        return ResponseData.success(userService.save(user));
    }

    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/delete/{userid}", method = RequestMethod.DELETE)
    public ResponseData delete(@ApiParam("Userid") @PathVariable Integer userid) {
        userService.removeById(userid);
        return ResponseData.success(userid);
    }

    @ApiOperation(value = "更改用户")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseData modifyUser(@RequestBody User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        User updateuser = userService.getById(user.getId());
        if(updateuser != null) {
            return ResponseData.success(userService.updateById(user));
        }else {
            return new ResponseData(false, 500, "用户不存在", null);
        }
    }

    @ApiOperation(value = "查询用户列表")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseData getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> users = userService.list(new QueryWrapper<User>().select("id", "account", "age", "email"));
        return ResponseData.success(users);
    }

    @ApiOperation(value = "获取用户详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseData getUser(@PathVariable Integer id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        User user = userService.getOne(new QueryWrapper<User>().select("id", "account", "age", "email").eq("id", id));
        return ResponseData.success(user);
    }

}
