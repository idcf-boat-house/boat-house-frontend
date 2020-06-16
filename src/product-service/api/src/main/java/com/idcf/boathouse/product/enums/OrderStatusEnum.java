package com.idcf.boathouse.product.enums;

/**
 * date:2020/3/18 21:57
 * author:sunlin
 * desc: 订单状态枚举
 */
public enum OrderStatusEnum {
    OrderExpired(-2, "订单已过期"),
    OrderRefued(-1, "订单被拒绝"),
    OrderWaitPay(0, "待支付"),
    OrderWaitHandle(1, "待受理"),
    OrderHandling(2, "派送中"),
    OrderCompleted(3, "已完成");

    private int value;
    private String desc;

    OrderStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }
}
