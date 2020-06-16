package com.idcf.boathouse.product.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Blob;

@ApiModel
public class Food {

    @ApiModelProperty(value = "菜品ID")
    public Integer Id;

    @ApiModelProperty(value = "菜品分类ID")
    public Integer CategoryId;

    @ApiModelProperty(value = "菜品名称")
    public String Name;

    @ApiModelProperty(value = "菜品价格")
    public BigDecimal Price;

    @ApiModelProperty(value = "菜品描述")
    public String Description;

    @ApiModelProperty(value = "菜品图片")
    public String Picture;

}
