package com.idcf.boathouse.product.services;

import com.idcf.boathouse.product.vo.OrderCreateVo;
import com.idcf.boathouse.product.vo.OrderItemsCreateVo;
import com.idcf.boathouse.product.vo.OrderVo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * date:2020/3/17 10:10
 * author:xiaokunliu
 * desc: junit4 test case for OrderServiceTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Profile("dev")
//@ContextConfiguration()
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return null;
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void create() throws Exception {
        OrderCreateVo orderCreateVo = new OrderCreateVo();
        orderCreateVo.setAdditionalAmount(new BigDecimal(20));
        orderCreateVo.setNote("不要辣");
        OrderItemsCreateVo itemVo = new OrderItemsCreateVo();
        itemVo.setFoodId(1);
        itemVo.setFoodNum(10);
        List<OrderItemsCreateVo> lstOrderItemsCreateVo = new ArrayList<>();
        lstOrderItemsCreateVo.add(itemVo);
        orderCreateVo.setItemsList(lstOrderItemsCreateVo);

        OrderVo orders = orderService.createOrder(orderCreateVo);
        Assert.assertEquals(70, orders.getTotalAmount().intValue());
    }

    // 单元测试通过
    @Test
    public void findPendingOrders() {
        List<OrderVo> orders = orderService.findPendingOrders(1, 10);
        for (OrderVo order : orders) {
            System.out.println(order);
            System.out.println(order.getItemsList());
        }
    }

    @Test
    public void confirm() {
        List<OrderVo> orders = orderService.findPendingOrders(1, 10);
        List<String> orderIds = new ArrayList<>();
        for (OrderVo order : orders) {
            orderIds.add(order.getOrderId());
        }
        Assert.assertEquals(1, orderService.confirmOrders(orderIds));
    }

    @Test
    public void refuse() {
        List<OrderVo> orders = orderService.findPendingOrders(1, 10);
        int res = orderService.refuseOrders(orders.get(0).getOrderId(), "the order have something wrong..");
        //Assert.assertEquals(1, res);
    }


}