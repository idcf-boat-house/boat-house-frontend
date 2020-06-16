package com.idcf.boathouse.product.controller;

import com.idcf.boathouse.product.services.OrderService;
import com.idcf.boathouse.product.untils.ActionResult;
import com.idcf.boathouse.product.vo.OrderCreateVo;
import com.idcf.boathouse.product.vo.OrderVo;
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
    public List<OrderVo> getPendingOrders(@Positive(message = "必须为大于0的正整数") @RequestParam(value = "index", defaultValue = "1") Integer pageIndex,
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

    /**
     * 下单接口，返回结果明确泛型使用的类是为了配合swagger生成接口文档
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ApiOperation("客户下单操作")
    public ActionResult<OrderVo> createOrder(@RequestBody OrderCreateVo order) {
        ActionResult result = null;
        try {
            OrderVo orderVo = orderService.createOrder(order);
            result = ActionResult.success(orderVo);
        } catch (Exception e) {
            result = ActionResult.fail(null, e.getMessage());
        }
        return result;
    }
}
