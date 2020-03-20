package com.idcf.boathouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idcf.boathouse.models.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Select("select * from idcf_orders where order_status=1 limit #{limited} offset #{offset}")
    List<Orders> findPendingOrders(@Param("offset") int offset, @Param("limited") int limited);
}
