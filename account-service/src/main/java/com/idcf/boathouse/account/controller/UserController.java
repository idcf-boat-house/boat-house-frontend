package com.idcf.boathouse.account.controller;

import com.idcf.boathouse.account.core.ResponseData;
import com.idcf.boathouse.account.entity.User;
import com.idcf.boathouse.account.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户管理接口")
@RequestMapping("/rest/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "增加用户")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseData createUser(@ModelAttribute User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        return ResponseData.success(userService.save(user));
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "/{userid}")
    public ResponseData delete(@ApiParam("Userid") @PathVariable String userid) {
        userService.removeById(userid);
        return ResponseData.success(userid);
    }

    @ApiOperation(value = "更改用户")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseData modifyUser(@PathVariable Long id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        user.setId(id);
        return ResponseData.success(userService.updateById(user));
    }

    @ApiOperation(value = "查询用户列表")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseData getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        return ResponseData.success(userService.list());
    }

    @ApiOperation(value = "获取用户详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseData getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return ResponseData.success(userService.getById(id));
    }

}
