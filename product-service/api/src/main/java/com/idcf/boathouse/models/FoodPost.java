package com.idcf.boathouse.models;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Blob;

public class FoodPost {
    public Integer id;

    public String name;

    public Integer categoryId;

    public BigDecimal price;

    public String description;

    public String picture;
}
