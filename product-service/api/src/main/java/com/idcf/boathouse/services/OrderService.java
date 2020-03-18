package com.idcf.boathouse.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idcf.boathouse.mapper.OrderItemsMapper;
import com.idcf.boathouse.mapper.OrdersMapper;
import com.idcf.boathouse.models.OrderItems;
import com.idcf.boathouse.models.Orders;
import com.idcf.boathouse.models.constant.OrderConstant;
import com.idcf.boathouse.untils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * date:2020/3/15 21:25
 * author:xiaokunliu
 * desc: business desc etc.
 */
@Service
public class OrderService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderItemsMapper orderItemsMapper;


    /**
     * 查询订单以及订单详细
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<Orders> findPendingOrders(int pageIndex, int pageSize) {
        List<Orders> ordersList = ordersMapper.findPendingOrders(pageIndex - 1, pageSize);
        QueryWrapper<OrderItems> queryWrapper = new QueryWrapper<>();
        for (Orders order : ordersList) {
            queryWrapper.clear();
            queryWrapper.eq("order_id", order.getOrderId());
            List<OrderItems> itemsList = orderItemsMapper.selectList(queryWrapper);
            order.setItemsList(itemsList);
            order.setOrderStatusDesc(OrderConstant.getOrderDesc(order.getOrderStatus()));
            order.setOrderTime(DateUtils.formatTime(order.getCreateTime()));
            order.setUpdateTimeStr(DateUtils.formatTime(order.getUpdateTime()));
        }
        return ordersList;
    }

    /**
     * 商家接单操作
     *
     * @param orderIdList
     * @return
     */
    public int confirmOrders(List<String> orderIdList) {
        if (null == orderIdList || orderIdList.isEmpty()) {
            return 0;
        }


        for (String orderId : orderIdList) {
            ordersMapper.confirmOrder(orderId);
        }
        return 1;
    }

    /**
     * 商家拒单操作
     *
     * @param orderId
     * @param reason
     * @return
     */
    public int refuseOrders(String orderId, String reason) {
        return ordersMapper.refuseOrder(orderId, reason);
    }

}
