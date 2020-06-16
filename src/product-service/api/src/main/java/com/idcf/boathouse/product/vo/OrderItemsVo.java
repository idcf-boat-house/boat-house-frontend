package com.idcf.boathouse.product.vo;

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
