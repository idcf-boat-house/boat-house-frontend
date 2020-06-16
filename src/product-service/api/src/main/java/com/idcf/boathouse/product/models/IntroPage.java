package com.idcf.boathouse.product.models;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "intropage")
public class IntroPage {
    @TableId(value = "page_id")
    private String pageId;
    @TableField(value = "page_title", insertStrategy = FieldStrategy.DEFAULT)
    private String pageTitle;
    @TableField(value = "page_api_url", insertStrategy = FieldStrategy.DEFAULT)
    private String pageApiUrl;
    @TableField(value = "text", insertStrategy = FieldStrategy.DEFAULT)
    private String text;
    @TableField(value = "image", insertStrategy = FieldStrategy.DEFAULT)
    private String image;
    @TableField(value = "deleted", insertStrategy = FieldStrategy.DEFAULT)
    private boolean deleted;
    @TableField(value = "create_time", insertStrategy = FieldStrategy.DEFAULT)
    private Date createTime;
    @TableField(value = "update_time", insertStrategy = FieldStrategy.DEFAULT)
    private Date updateTime;
}
