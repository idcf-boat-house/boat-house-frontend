package com.idcf.boathouse.product.enums;

public enum ResponseEnum {
    Success(1, "成功"),
    Fail(0, "失败");

    private int value;
    private String desc;

    ResponseEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
