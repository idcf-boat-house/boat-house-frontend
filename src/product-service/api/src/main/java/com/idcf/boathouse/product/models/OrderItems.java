package com.idcf.boathouse.product.models;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * date:2020/3/15 13:44
 * author:xiaokunliu
 * desc: business desc etc.
 */
@Data
@TableName(value = "idcf_order_items")
public class OrderItems {

    @TableId(value = "id", type = IdType.AUTO)
    private long id;

    @TableField(value = "order_id", insertStrategy = FieldStrategy.DEFAULT)
    private String orderId;

    @TableField(value = "food_id", insertStrategy = FieldStrategy.DEFAULT)
    private int foodId;

    @TableField(value = "food_name", insertStrategy = FieldStrategy.DEFAULT)
    private String foodName;

    @TableField(value = "food_price", insertStrategy = FieldStrategy.DEFAULT)
    private BigDecimal foodPrice;

    @TableField(value = "food_sub_total", insertStrategy = FieldStrategy.DEFAULT)
    private BigDecimal foodSubTotal;

    @TableField(value = "food_num", insertStrategy = FieldStrategy.DEFAULT)
    private int foodNum;

    @TableField(value = "food_picture", insertStrategy = FieldStrategy.DEFAULT)
    private String foodPicture;
}
