package com.idcf.boathouse.controller;

import com.idcf.boathouse.services.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * date:2020/3/15 21:57
 * author:xiaokunliu
 * desc: business desc etc.
 */
@RestController
@Api(tags = "订单接口")
@RequestMapping("/order/*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "list/pending", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("查询商户未接单的订单列表")
    public List<Map<String, Object>> getPendingOrders(@RequestParam(value = "页码") Integer pageIndex,
                                                      @RequestParam(value = "页面条目数") Integer pageSize) {
        return null;
    }


    @RequestMapping(value = "confirm", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("商户接单操作[支持批量,同时有上限限制]")
    public void confirm(@RequestParam(value = "orderIdList") List<String> orderIdList) {

    }

    @RequestMapping(value = "refuse", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @ApiOperation("商户拒单操作")
    public void refuse(@RequestParam(value = "order_id") String orderId, @RequestParam(value = "reason") String reason) {

    }
}
