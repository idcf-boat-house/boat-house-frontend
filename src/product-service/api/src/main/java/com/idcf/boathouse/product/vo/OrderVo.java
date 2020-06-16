package com.idcf.boathouse.product.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
添加了额外信息，用来返回给前端
 */
@Data
public class OrderVo {

    private long id;

    private long userId;

    private String orderId;

    private Date createTime;

    private Date updateTime;

    private int payType;

    private BigDecimal totalAmount;

    private BigDecimal additionalAmount;

    private int orderStatus;

    private String reason;

    private String note;

    // for frontend
    private List<OrderItemsVo> itemsList;

    private String orderStatusDesc;

    private String orderTime;

    private String updateTimeStr;
}
