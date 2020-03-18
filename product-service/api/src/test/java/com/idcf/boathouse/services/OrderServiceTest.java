package com.idcf.boathouse.services;

import com.idcf.boathouse.models.Orders;
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

    // 单元测试通过
    @Test
    public void findPendingOrders() {
        List<Orders> orders = orderService.findPendingOrders(1, 10);
        for (Orders order : orders) {
            System.out.println(order);
            System.out.println(order.getItemsList());
        }
    }

    @Test
    public void confirm() {
        List<Orders> orders = orderService.findPendingOrders(0, 10);
        List<String> orderIds = new ArrayList<>();
        for (Orders order : orders) {
            orderIds.add(order.getOrderId());
        }
        Assert.assertEquals(1, orderService.confirmOrders(orderIds));
    }

    @Test
    public void refuse() {
        List<Orders> orders = orderService.findPendingOrders(0, 1);
        Assert.assertEquals(1, orders.size());
        Assert.assertNotNull(orders.get(0).getOrderId());
        int res = orderService.refuseOrders(orders.get(0).getOrderId(), "the order have something wrong..");
        Assert.assertEquals(1, res);
    }
}