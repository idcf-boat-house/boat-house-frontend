package com.idcf.boathouse.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 用做创建订单接口的参数，排除不必要的字段，接口清晰
 */
@Data
public class OrderItemsCreateVo {

    private int foodId;

    private String foodName;

    private BigDecimal foodPrice;

    private int foodNum;

    private String foodPicture;
}
