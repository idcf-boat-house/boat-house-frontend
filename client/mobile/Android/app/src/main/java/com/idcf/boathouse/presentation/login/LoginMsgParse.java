package com.idcf.boathouse.presentation.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class LoginMsgParse {
    public String getToken(String result) {
        com.alibaba.fastjson.JSONObject object = JSON.parseObject(result, JSONObject.class);
        return object.getJSONObject("data").getString("token");
    }

}
