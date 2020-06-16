package com.idcf.boathouse.product.models;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * date:2020/3/15 13:37
 * author:xiaokunliu
 * desc: business desc etc.
 */
@Data
@TableName(value = "idcf_orders")
public class Orders {

    @TableId(value = "id", type = IdType.AUTO)
    private long id;

    @TableField(value = "user_id", insertStrategy = FieldStrategy.DEFAULT)
    private long userId;

    @TableField(value = "order_id", insertStrategy = FieldStrategy.DEFAULT)
    private String orderId;

    @TableField(value = "create_time", insertStrategy = FieldStrategy.DEFAULT)
    private Date createTime;

    @TableField(value = "update_time", insertStrategy = FieldStrategy.DEFAULT)
    private Date updateTime;

    @TableField(value = "pay_type", insertStrategy = FieldStrategy.DEFAULT)
    private int payType;

    @TableField(value = "total_amount", insertStrategy = FieldStrategy.DEFAULT)
    private BigDecimal totalAmount;

    @TableField(value = "additional_amount", insertStrategy = FieldStrategy.DEFAULT)
    private BigDecimal additionalAmount;

    @TableField(value = "order_status", insertStrategy = FieldStrategy.DEFAULT)
    private int orderStatus;

    @TableField(value = "reason", insertStrategy = FieldStrategy.DEFAULT)
    private String reason;

    @TableField(value = "note", insertStrategy = FieldStrategy.DEFAULT)
    private String note;
}
