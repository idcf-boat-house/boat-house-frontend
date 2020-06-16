package com.idcf.boathouse.product.vo;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用做创建订单接口的参数，排除不必要的字段，接口清晰
 */
@Data
public class OrderCreateVo {
    private long userId;

    private String userName;

    private BigDecimal additionalAmount;

    private String note;
    // for frontend
    private List<OrderItemsCreateVo> itemsList;
}
