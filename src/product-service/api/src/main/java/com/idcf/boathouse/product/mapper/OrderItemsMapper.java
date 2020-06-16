package com.idcf.boathouse.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idcf.boathouse.product.models.OrderItems;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * date:2020/3/15 21:24
 * author:xiaokunliu
 * desc: business desc etc.
 */
@Repository
@Mapper
public interface OrderItemsMapper extends BaseMapper<OrderItems> {

}
