package com.idcf.boathouse.product.controller;

import com.idcf.boathouse.product.models.ShopCartPost;
import com.idcf.boathouse.product.models.ShopCartPut;
import com.idcf.boathouse.product.services.ShopCartService;
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
public class ShopCartController extends BaseController {

    @Autowired
    public ShopCartService shopCartService;

    @RequestMapping(value = "ShopCart", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("添加菜品到购物车")
    public Map<String, Object> addFoodToShopCart(@RequestBody ShopCartPost shopCart) throws IllegalStateException, IOException {
        ShopCartPost shopCartPost = new ShopCartPost();
        shopCartPost.userid = shopCart.userid;
        shopCartPost.foodid = shopCart.foodid;
        shopCartPost.num = shopCart.num;
        shopCartPost.comment = shopCart.comment;
        shopCartService.insertOrUpdateShopCart(shopCartPost);
        return super.info(BaseController.CODE_OK, "添加购物车成功", null);
    }

    @RequestMapping(value = "ShopCartAddFoodNum", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("增加购物车菜品数量")
    public Map<String, Object> addFoodNum(@RequestBody ShopCartPut shopCartPut) throws IllegalStateException, IOException {

        ShopCartPost shopCartPost = new ShopCartPost();
        shopCartPost.userid = shopCartPut.getUserId();
        shopCartPost.foodid = shopCartPut.getFoodID();
        shopCartPost.num = shopCartPut.getNum();
        shopCartService.insertOrUpdateShopCart(shopCartPost);
        return super.info(BaseController.CODE_OK, "+" + shopCartPut.getNum().toString(), null);
    }

    @RequestMapping(value = "ShopCartReduceFoodNum", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("减少购物车菜品数量")
    public Map<String, Object> reduceFoodNum(@RequestBody ShopCartPut shopCartPut) throws IllegalStateException, IOException {

        shopCartService.reduceFoodNum(shopCartPut.getUserId(), shopCartPut.getFoodID(), shopCartPut.getNum());
        return super.info(BaseController.CODE_OK, "-" + shopCartPut.getNum().toString(), null);
    }

    @RequestMapping(value = "ShopCart", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("从购物车删除菜品")
    public Map<String, Object> DeleteFoodFromShopCart(@RequestParam Integer userId,
                                                      @RequestParam Integer foodID) {
        shopCartService.DeleteFoodFromShopCart(userId, foodID);
        return super.info(BaseController.CODE_OK, "从购物车删除菜品成功", null);
    }


    @RequestMapping(value = "ShopCart", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("获取所有购物车菜品列表")
    public Map<String, Object> GetFoodListFromShopCart(@RequestParam(required = false) Integer userId) {
        List<Map<String, Object>> list = shopCartService.listFoodsInCart(userId);
        return super.info(BaseController.CODE_OK, "获取购物车列表成功", list);
    }

    @RequestMapping(value = "ShopCart", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("清空购物车")
    public Map<String, Object> ClearShopCart(@RequestParam Integer userId) {
        shopCartService.ClearShopCart(userId);
        return super.info(BaseController.CODE_OK, "清空购物车成功", null);
    }

}
