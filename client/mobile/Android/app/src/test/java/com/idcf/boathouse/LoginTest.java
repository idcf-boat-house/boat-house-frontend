package com.idcf.boathouse;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.idcf.boathouse.presentation.login.LoginActivity;


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
        LoginActivity activity = new LoginActivity();
        String str = "{\"success\":true,\"code\":200,\"token\":123321,\"message\":\"............\",\"data\":{\"result\":true}}";
        assertEquals("123321", activity.getToken(str));
    }


}