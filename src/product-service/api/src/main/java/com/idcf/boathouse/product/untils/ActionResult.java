package com.idcf.boathouse.product.untils;

import com.idcf.boathouse.product.enums.ResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
//@Data
public class ActionResult<T> implements Serializable {
    @ApiModelProperty(value = "返回code，0为失败，1为成功。")
    private int code;
    @ApiModelProperty(value = "返回成功时，查看data数据。")
    private T data;
    @ApiModelProperty(value = "返回失败时，查看errorMsg。")
    private String errorMsg;

    public static <T> ActionResult<T> success(T data) {
        ActionResult result = new ActionResult();
        result.setData(data);
        result.setCode(ResponseEnum.Success.getValue());
        return result;
    }

    public static <T> ActionResult<T> fail(T data, String errorMsg) {
        ActionResult result = new ActionResult();
        result.setData(data);
        result.setErrorMsg(errorMsg);
        result.setCode(ResponseEnum.Fail.getValue());
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
