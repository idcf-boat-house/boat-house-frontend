package com.idcf.boathouse.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idcf.boathouse.product.models.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * date:2020/3/15 21:23
 * author:xiaokunliu
 * desc: business desc etc.
 */
@Repository
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    @Select("select * from idcf_orders  order by update_time desc limit #{limited} offset #{offset}")
    List<Orders> findPendingOrders(@Param("offset") int offset, @Param("limited") int limited);

    // 必须只有pending order才能进行更改
    @Update("update idcf_orders set order_status=2,update_time=NOW() where order_id=#{orderId} AND order_status=1")
    int confirmOrder(@Param("orderId") String orderId);

    @Update("update idcf_orders set order_status=-1, reason=#{reason}, update_time=NOW() where order_id=#{orderId} AND order_status=1")
    int refuseOrder(@Param("orderId") String orderId, @Param("reason") String reason);

    @Select("select count(1) from idcf_orders where to_days((create_time)) = to_days(now()) ")
    Long getOrderCountToday();
}
