package com.idcf.boathouse.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class FoodCategory {

    @ApiModelProperty(value = "菜品分类ID")
    public Integer Id;

    @ApiModelProperty(value = "菜品分类名称")
    public String Name;

    @ApiModelProperty(value = "菜品分类描述")
    public String Description;

}
