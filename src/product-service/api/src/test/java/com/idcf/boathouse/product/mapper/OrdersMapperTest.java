package com.idcf.boathouse.product.mapper;

import com.idcf.boathouse.product.models.Orders;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * date:2020/3/15 22:18
 * author:xiaokunliu
 * desc: junit4 test case for OrdersMapperTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Profile(value = "dev")
//@ContextConfiguration()
@SpringBootTest
public class OrdersMapperTest {

    @Autowired
    private OrdersMapper ordersMapper;

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
    public void testInsert() {
        // 本地测试通过
        for (int index = 0; index < 100; index++) {
            Orders orders = new Orders();
            orders.setOrderId("xxxxxx" + (index + 1));
            orders.setAdditionalAmount(new BigDecimal("10.00"));
            orders.setCreateTime(new Date());
            orders.setOrderStatus(1);
            orders.setPayType(2);
            orders.setUpdateTime(new Date());
            orders.setTotalAmount(new BigDecimal("90.23"));
            orders.setUserId(19);
            ordersMapper.insert(orders);
        }
    }

    @Test
    public void findPendingOrders() {
        // 本地测试通过
        List<Orders> orders = ordersMapper.findPendingOrders(0, 10);
        System.out.println(orders.size());
    }
}
