package com.idcf.boathouse.controller;

import com.idcf.boathouse.models.ShopCartPost;
import com.idcf.boathouse.services.ShopCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;



@RestController
@Api(tags = "Boat House ShopCart API")
@RequestMapping("/BoatHouse/*")
public class ShopCartController extends  BaseController {

    @Autowired
    public ShopCartService shopCartService;

    @RequestMapping(value= "ShopCart", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("添加菜品到购物车")
    public Map<String, Object> addFoodToShopCart(@RequestBody ShopCartPost shopCart)  throws IllegalStateException, IOException {
        ShopCartPost shopCartPost = new ShopCartPost();
        shopCartPost.userid = shopCart.userid;
        shopCartPost.foodid = shopCart.foodid;
        shopCartPost.num = shopCart.num;
        shopCartPost.comment = shopCart.comment;
        shopCartService.insertOrUpdateShopCart(shopCartPost);
        return super.info(BaseController.CODE_OK,"添加购物车成功", null);
    }

    @RequestMapping(value = "ShopCartAddFoodNum", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("增加购物车菜品数量")
    public  Map<String, Object> addFoodNum(@RequestParam(value = "用户ID") Integer userId,
                                           @RequestParam(value = "菜品ID") Integer foodID,
                                           @RequestParam(value = "增加数量") Integer addNum
                                           )
            throws IllegalStateException, IOException {

        ShopCartPost shopCartPost = new ShopCartPost();
        shopCartPost.userid = userId;
        shopCartPost.foodid = foodID;
        shopCartPost.num = addNum;
        shopCartService.insertOrUpdateShopCart(shopCartPost);
        return super.info(BaseController.CODE_OK,"+"+addNum.toString(), null);
    }

    @RequestMapping(value = "ShopCartReduceFoodNum", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("减少购物车菜品数量")
    public  Map<String, Object> reduceFoodNum(@RequestParam(value = "用户ID") Integer userId,
                                              @RequestParam(value = "菜品ID") Integer foodID,
                                              @RequestParam(value = "减少数量") Integer reduceNum
           )throws IllegalStateException, IOException {

        shopCartService.reduceFoodNum(userId,foodID,reduceNum);
        return super.info(BaseController.CODE_OK,"-"+reduceNum.toString(), null);
    }

    @RequestMapping(value = "ShopCart", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("从购物车删除菜品")
    public Map<String, Object> DeleteFoodFromShopCart(@RequestParam Integer userId,
                                                      @RequestParam Integer foodID){
        shopCartService.DeleteFoodFromShopCart(userId,foodID);
        return super.info(BaseController.CODE_OK,"从购物车删除菜品成功", null);
    }


    @RequestMapping(value = "ShopCart", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("获取所有购物车菜品列表")
    public Map<String, Object> GetFoodListFromShopCart(@RequestParam(required = false) Integer userId){
        List<Map<String, Object>> list = shopCartService.listFoodsInCart(userId);
        return super.info(BaseController.CODE_OK, "获取购物车列表成功", list);
    }

    @RequestMapping(value = "ShopCart", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("清空购物车")
    public Map<String, Object> ClearShopCart(@RequestParam Integer userId){
        shopCartService.ClearShopCart(userId);
        return super.info(BaseController.CODE_OK, "清空购物车成功", null);
    }

}
