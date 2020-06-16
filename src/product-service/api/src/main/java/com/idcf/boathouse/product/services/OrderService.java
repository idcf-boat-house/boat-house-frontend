package com.idcf.boathouse.product.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idcf.boathouse.product.enums.OrderStatusEnum;
import com.idcf.boathouse.product.mapper.OrderItemsMapper;
import com.idcf.boathouse.product.mapper.OrdersMapper;
import com.idcf.boathouse.product.models.Food;
import com.idcf.boathouse.product.models.OrderItems;
import com.idcf.boathouse.product.models.Orders;
import com.idcf.boathouse.product.models.constant.OrderConstant;
import com.idcf.boathouse.product.untils.DateUtils;
import com.idcf.boathouse.product.vo.OrderCreateVo;
import com.idcf.boathouse.product.vo.OrderItemsCreateVo;
import com.idcf.boathouse.product.vo.OrderItemsVo;
import com.idcf.boathouse.product.vo.OrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private FoodService foodService;


    /**
     * 查询订单以及订单详细
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<OrderVo> findPendingOrders(int pageIndex, int pageSize) {
        List<OrderVo> lstOrderVo = new ArrayList<>();

        List<Orders> ordersList = ordersMapper.findPendingOrders(pageIndex - 1, pageSize);
        QueryWrapper<OrderItems> queryWrapper = new QueryWrapper<>();
        for (Orders order : ordersList) {
            OrderVo orderVo = new OrderVo();
            BeanUtils.copyProperties(order, orderVo);
            queryWrapper.clear();
            queryWrapper.eq("order_id", order.getOrderId());
            List<OrderItems> itemsList = orderItemsMapper.selectList(queryWrapper);
            List<OrderItemsVo> lstOrderItemsVo = new ArrayList<>();
            for (OrderItems orderItems : itemsList) {
                OrderItemsVo orderItemsVo = new OrderItemsVo();
                BeanUtils.copyProperties(orderItems, orderItemsVo);
                lstOrderItemsVo.add(orderItemsVo);
            }

            orderVo.setItemsList(lstOrderItemsVo);
            orderVo.setOrderStatusDesc(OrderConstant.getOrderDesc(order.getOrderStatus()));
            orderVo.setOrderTime(DateUtils.formatTime(order.getCreateTime()));
            orderVo.setUpdateTimeStr(DateUtils.formatTime(order.getUpdateTime()));
            lstOrderVo.add(orderVo);
        }
        return lstOrderVo;
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

    /**
     * 客户下单操作
     *
     * @param orderCreateVo
     * @return
     */
    @Transactional
    public OrderVo createOrder(OrderCreateVo orderCreateVo) throws Exception {
        //先插入一个订单获得订单的id，然后更新订单信息。
        Orders order = new Orders();
        //复制字段信息
        BeanUtils.copyProperties(orderCreateVo, order);
        order.setCreateTime(new Date());
        order.setOrderStatus(OrderStatusEnum.OrderWaitPay.getValue());
        order.setUpdateTime(new Date());
        String id = generateOrderId();
        order.setOrderId(id);
        BigDecimal total = new BigDecimal(0);


        for (OrderItemsCreateVo orderItemsCreateVo : orderCreateVo.getItemsList()) {
            Food food = foodService.getFood(orderItemsCreateVo.getFoodId() + "");
            if (food == null) {
                throw new Exception("菜品Id" + orderItemsCreateVo.getFoodId() + "不存在");
            }
            OrderItems orderItems = new OrderItems();
            if (orderItemsCreateVo.getFoodNum() != 0 && food.Price != null) {
                BigDecimal subTotal = new BigDecimal(orderItemsCreateVo.getFoodNum()).multiply(food.Price);
                total = total.add(subTotal);
                orderItems.setFoodSubTotal(subTotal);
            }
            orderItems.setOrderId(id);
            orderItems.setFoodId(orderItemsCreateVo.getFoodId());
            orderItems.setFoodNum(orderItemsCreateVo.getFoodNum());
            orderItems.setFoodPicture(food.Picture);
            orderItems.setFoodName(food.Name);
            orderItems.setFoodPrice(food.Price);
            orderItemsMapper.insert(orderItems);
        }
        if (orderCreateVo.getAdditionalAmount() != null) {
            total = total.add(orderCreateVo.getAdditionalAmount());
        }
        order.setTotalAmount(total);
        ordersMapper.insert(order);

        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(order, orderVo);
        orderVo.setOrderStatusDesc(OrderStatusEnum.OrderWaitHandle.getDesc());
        orderVo.setOrderTime(DateUtils.formatTime(order.getCreateTime()));
        orderVo.setUpdateTimeStr(DateUtils.formatTime(order.getUpdateTime()));
        //TODO 需要从用户相关方法取到当前用户信息
        orderVo.setUserId(1L);
        return orderVo;
    }

    /**
     * 获得当天的订单数量，用于生成单号
     *
     * @return
     */
    public Long getOrderCountToday() {
        return ordersMapper.getOrderCountToday();
    }

    /**
     * 用于生成单号
     *
     * @return
     */
    public String generateOrderId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(new Date());
        Long count = getOrderCountToday();
        //序号补全0组成8位数组后和日期拼接，格式举例2020031800000023
        String id = String.format("%s%08d", today, count + 1L);
        return id;
    }
}
