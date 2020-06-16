package com.idcf.boathouse.product.models;

import io.swagger.annotations.ApiModelProperty;

public class IntroPageFront {
    @ApiModelProperty(value = "介绍页ID")
    public String page_id;
    @ApiModelProperty(value = "介绍页title")
    public String page_title;
    @ApiModelProperty(value = "介绍页后台调用地址")
    public String page_api_url;
    @ApiModelProperty(value = "介绍页文字内容和图片地址")
    public IntroPageValues page_values;
}
