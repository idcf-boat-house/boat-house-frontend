package com.idcf.boathouse.services;

import com.idcf.boathouse.mapper.OrderItemsMapper;
import com.idcf.boathouse.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<Map<String, Object>> findPendingOrders(int pageIndex, int pageSize) {
        return null;
    }
}
