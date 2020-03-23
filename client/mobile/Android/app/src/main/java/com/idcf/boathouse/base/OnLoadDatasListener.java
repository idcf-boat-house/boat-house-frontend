package com.idcf.boathouse.base;

/**
 * @author JaneConan
 * 描述：
 * 创建日期：2020/03/22
 *
 */
public interface OnLoadDatasListener<T> {

    /**
     * 成功
     * @param t 数据
     */
    void onSuccess(T t);


    /**
     * 失败
     * @param error 错误信息
     */
    void onFailure(String error);

}
