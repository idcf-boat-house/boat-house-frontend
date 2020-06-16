package com.idcf.boathouse.product.models;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Blob;

/**
 * @author yan.wang
 */
@ApiModel
public class ShopCart {

    @ApiModelProperty(value = "购物车ID")
    public String Id;

    @ApiModelProperty(value = "用户ID")
    public Integer UserId;

    @ApiModelProperty(value = "菜品ID")
    public Integer FoodId;

    @ApiModelProperty(value = "菜品数量")
    public Integer Num;

    @ApiModelProperty(value = "购物车描述")
    public String comment;
}
