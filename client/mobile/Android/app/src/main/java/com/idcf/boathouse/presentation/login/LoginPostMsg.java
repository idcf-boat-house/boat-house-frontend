package com.idcf.boathouse.presentation.login;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginPostMsg {
    public String postMsg(URL url, String username, String password) throws IOException {
        com.alibaba.fastjson.JSONObject object = new com.alibaba.fastjson.JSONObject();

        object.put("password", password);
        object.put("username", username);
        String data = object.toString();
        HttpURLConnection connection = getHttpURLConnection(url, data);
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(data.getBytes());
        //获得结果码
        int responseCode = connection.getResponseCode();
        if(responseCode ==200){
            return getSuccessMsg(connection);
        }else {
            //请求失败
            return String.valueOf(responseCode);
        }
    }

    private HttpURLConnection getHttpURLConnection(URL url, String data) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestMethod("POST");

        //至少要设置的两个请求头
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", String.valueOf(data.length()));

        //post的方式提交实际上是留的方式提交给服务器
        connection.setDoOutput(true);
        return connection;
    }

    private String getSuccessMsg(HttpURLConnection connection) throws IOException {
        //请求成功
        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String msg = br.readLine();
        Log.d("result=",msg);
        return msg;
    }



}
