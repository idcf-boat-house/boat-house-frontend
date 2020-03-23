package com.idcf.boathouse.vo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderItemsVo {
    private long id;

    private String orderId;

    private int foodId;

    private String foodName;

    private BigDecimal foodPrice;

    private BigDecimal foodSubTotal;

    private int foodNum;

    private String foodPicture;
}
