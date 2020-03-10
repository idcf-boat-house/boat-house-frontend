package com.idcf.boathouse.controller;

import com.idcf.boathouse.models.Orders;
import com.idcf.boathouse.services.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Map;

/**
 * date:2020/3/15 21:57
 * author:xiaokunliu
 * desc: business desc etc.
 */
@RestController
@Validated
@Api(tags = "订单接口")
@RequestMapping("/orders/*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "pending", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("查询商户未接单的订单列表")
    public List<Orders> getPendingOrders(@Positive(message = "必须为大于0的正整数") @RequestParam(value = "index", defaultValue = "1") Integer pageIndex,
                                         @Positive(message = "必须为大于0的正整数") @RequestParam(value = "size", defaultValue = "20") Integer pageSize) {
        return orderService.findPendingOrders(pageIndex, pageSize);
    }

    @RequestMapping(value = "confirm", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("商户接单操作")
    public void confirm(@RequestBody List<String> orderIdList) {
        // post_data: ["xxxxxx21"]
        orderService.confirmOrders(orderIdList);
    }

    @RequestMapping(value = "refuse", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("商户拒单操作")
    public void refuse(@RequestBody Map<String, String> map) {
        //post_data: {"order_id": "xxxxxx23", "reason": "没有菜品"}
        String orderId = map.get("order_id");
        String reason = map.get("reason");
        orderService.refuseOrders(orderId, reason);
    }
}
