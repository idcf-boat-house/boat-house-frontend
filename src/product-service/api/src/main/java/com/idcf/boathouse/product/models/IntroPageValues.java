package com.idcf.boathouse.product.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sunlin on 3/1/2020.
 */
@ApiModel
public class IntroPageValues {
    @ApiModelProperty(value = "介绍页文字内容")
    public String text;
    @ApiModelProperty(value = "介绍页图片地址")
    public String image;
}
