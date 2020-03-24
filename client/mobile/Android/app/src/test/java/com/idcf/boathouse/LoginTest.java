package com.idcf.boathouse;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.idcf.boathouse.presentation.login.LoginActivity;
import com.idcf.boathouse.presentation.login.LoginMsgParse;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoginTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void gettoken()  {
        LoginMsgParse loginMsgParse = new LoginMsgParse();
        String str = "{\"success\":true,\"code\":200,\"message\":\"............\",\"data\":{\"userId\":null,\"token\":\"1234567890\",\"username\":\"1234\"}}";
        assertEquals("1234567890", loginMsgParse.getToken(str));
    }


}