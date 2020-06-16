package com.idcf.boathouse.product.untils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * date:2020/3/17 18:16
 * author:xiaokunliu
 * desc: business desc etc.
 */
public final class DateUtils {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatTime(Date date) {
        return dateFormat.format(date);
    }
}
