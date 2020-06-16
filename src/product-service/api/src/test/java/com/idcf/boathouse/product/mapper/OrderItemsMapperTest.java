package com.idcf.boathouse.product.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idcf.boathouse.product.models.OrderItems;
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
import java.util.List;
import java.util.Random;

/**
 * date:2020/3/15 23:20
 * author:xiaokunliu
 * desc: junit4 test case for OrderItemsMapperTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration()
@Profile("dev")
@SpringBootTest
public class OrderItemsMapperTest {

    @Autowired
    private OrderItemsMapper orderItemsMapper;

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
        List<Orders> orders = ordersMapper.findPendingOrders(0, 100);
        for (Orders order : orders) {
            testInsertItem(order.getOrderId(), new Random().nextInt(100));
        }
    }

    private void testInsertItem(String orderId, int randomId) {
        OrderItems item = new OrderItems();
        if (randomId == 0) {
            randomId = 1;
        }
        item.setFoodId(randomId);
        item.setFoodNum(randomId % 10);
        item.setFoodName(orderId + "---" + "food_name" + "---" + randomId);
        item.setFoodPicture("");
        item.setFoodPrice(new BigDecimal("23.00"));
        item.setOrderId(orderId);
        item.setFoodSubTotal(item.getFoodPrice().multiply(new BigDecimal(item.getFoodNum())));
        orderItemsMapper.insert(item);
    }

    @Test
    public void testUpdateItem() {
        // 本地测试通过
        QueryWrapper wrapper = new QueryWrapper<OrderItems>();
        List<OrderItems> itemsList = orderItemsMapper.selectList(wrapper);
        System.out.println(itemsList.size());
        for (OrderItems item : itemsList) {
            if (item.getFoodNum() == 0) {
                item.setFoodNum(1);
                item.setFoodSubTotal(item.getFoodPrice().multiply(new BigDecimal(item.getFoodNum())));
                orderItemsMapper.updateById(item);
            }
        }
    }


    @Test
    public void testPriceCount() {
        BigDecimal count = new BigDecimal(4);
        BigDecimal price = new BigDecimal("23.00");
        BigDecimal rs = count.multiply(price);
        System.out.println(rs.doubleValue());
    }
}