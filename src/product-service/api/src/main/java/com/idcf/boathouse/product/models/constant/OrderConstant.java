package com.idcf.boathouse.product.models.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * date:2020/3/17 11:49
 * author:xiaokunliu
 * desc: business desc etc.
 */
public final class OrderConstant {

    private final static Map<Integer, String> ORDER_STATUS = new HashMap<>();

    static {
        initStatus();
    }

    private static void initStatus() {
        ORDER_STATUS.put(-2, "订单过期失效");
        ORDER_STATUS.put(-1, "订单被拒绝");
        ORDER_STATUS.put(0, "待支付");
        ORDER_STATUS.put(1, "待受理");
        ORDER_STATUS.put(2, "派送中");
        ORDER_STATUS.put(3, "已完成");
    }

    public static String getOrderDesc(int orderStatus) {
        return ORDER_STATUS.get(orderStatus);
    }
}
